package com.github.lupaev.etran.service;

import com.github.lupaev.etran.builder.SoapRequestBuilder;
import com.github.lupaev.etran.client.Client;
import com.github.lupaev.etran.dto.*;
import com.github.lupaev.etran.dto.Error;
import com.github.lupaev.etran.entity.Skpp;
import com.github.lupaev.etran.entity.SkppStrs;
import com.github.lupaev.etran.entity.XmlQueryLog;
import com.github.lupaev.etran.exception.AuthException;
import com.github.lupaev.etran.exception.XmlProcessingException;
import com.github.lupaev.etran.exception.XmlTypeException;
import com.github.lupaev.etran.mapper.SkppMapper;
import com.github.lupaev.etran.repository.SkppRepository;
import com.github.lupaev.etran.repository.XmlQueryLogRepository;
import com.github.lupaev.etran.specification.SkppSpecification;
import com.github.lupaev.etran.util.XmlUtility;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.lupaev.etran.util.AppConstants.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class SkppServiceImpl implements SkppService {

	private final Client client;
	private final SkppRepository skppRepository;
	private final XmlQueryLogRepository xmlQueryLogRepository;
	private final SkppMapper skppMapper;

	@Value("${etran.login}")
	private String login;

	@Value("${etran.password}")
	private String password;

	/**
	 * Обрабатывает операцию SetBlock для всех сущностей SKPP
	 */
	@Override
	@Transactional(rollbackFor = {XmlProcessingException.class, XmlTypeException.class,
			DataIntegrityViolationException.class, EntityNotFoundException.class})
	public void processSetBlock() {
		List<Skpp> skpps = skppRepository.findAll(SkppSpecification.filterSkppForSetSkpp());
		if (skpps.isEmpty()) return;

		for (Skpp skpp : skpps) {
			try {
				Object rootElement = executeRequestAndGetResponse(skppMapper.toSetSkpp(skpp), skpp);
				processResponseSetSKPPReply(rootElement, skpp);
			} catch (Exception e) {
				log.error("Ошибка при обработке SetBlock для Skpp с ID {}: {}", skpp.getId(), e.getMessage(), e);
			}
		}
		skppRepository.saveAll(skpps);
	}

	/**
	 * Обрабатывает операцию GetBlock для всех сущностей SKPP
	 */
	@Override
	@Transactional(rollbackFor = {XmlProcessingException.class, XmlTypeException.class,
			DataIntegrityViolationException.class, EntityNotFoundException.class})
	public void processGetBlock() {
		List<Skpp> skpps = skppRepository.findAll(SkppSpecification.filterSkppForGetSkpp());
		if (skpps.isEmpty()) return;

		for (Skpp skpp : skpps) {
			try {
				Object rootElement = executeRequestAndGetResponse(skppMapper.toGetSkpp(skpp), skpp);
				processResponseGetSKPPReply(rootElement, skpp);
			} catch (Exception e) {
				log.error("Ошибка при обработке GetBlock для Skpp с ID {}: {}", skpp.getId(), e.getMessage(), e);
			}
		}
		skppRepository.saveAll(skpps);
	}

	/**
	 * Обрабатывает ответ SetSKPPReply от Этран.
	 *
	 * @param rootElement корневой элемент ответа
	 * @param skpp        сущность SKPP, которая обрабатывается
	 */
	private void processResponseSetSKPPReply(Object rootElement, Skpp skpp) {
		if (rootElement instanceof Error errorResponse) {
			skpp.setIsRun(ERROR_RUN_VALUE);
			skpp.setRespText(errorResponse.getErrorMessage().getValue());
		} else if (rootElement instanceof SetSKPPReply setSkppReply) {
			if (setSkppReply.getResult() == 1) {
				skpp.setIsRun(SUCCESS_RUN_VALUE);
			} else {
				skpp.setIsRun(ERROR_RUN_VALUE);
				skpp.setRespText(setSkppReply.getWarning());
			}
		} else {
			throw new XmlTypeException(UNKNOWN_TYPE_ANSWER);
		}
		skpp.setDateSent(LocalDateTime.now());
	}

	/**
	 * Обрабатывает ответ GetSKPPReply от Этран.
	 *
	 * @param rootElement корневой элемент ответа
	 * @param skpp сущность SKPP, которая обрабатывается
	 */
	private void processResponseGetSKPPReply(Object rootElement, Skpp skpp) {
		if (rootElement instanceof Error errorResponse) {
			skpp.setRespText(errorResponse.getErrorMessage().getValue());
		} else if (rootElement instanceof GetSKPPReply getSkppReply) {
			processGetSKPPReply(getSkppReply, skpp);
		} else {
			throw new XmlTypeException(UNKNOWN_TYPE_ANSWER);
		}
		skpp.setDateSent(LocalDateTime.now());
	}

	/**
	 * Обрабатывает ответ GetSKPPReply
	 *
	 * @param getSkppReply ответ GetSKPPReply
	 * @param skpp сущность SKPP, которая обрабатывается
	 */
	private void processGetSKPPReply(GetSKPPReply getSkppReply, Skpp skpp) {
		LocalDateTime now = LocalDateTime.now();
		Map<String, SkppStrs> skppStrsMap = mapSkppStrs(skpp);

		Boolean allFieldsFilled = null;

		for (ClientRow clientRow : getSkppReply.getClientRows()) {
			if (clientRow.getRows() == null || clientRow.getRows().isEmpty()) continue;

			for (Row row : clientRow.getRows()) {
				SkppStrs skppStrs = findSkppStrs(skppStrsMap, row);
				if (skppStrs != null) {
					skppMapper.updateSkppStrs(skppStrs, row);
					skppStrs.setUpdateDate(now);
					if (isLoadingFieldsFilled(row)) {
						allFieldsFilled = true;
						skpp.setDateAccept(now);
					} else if (isAgreedFieldsFilled(row)) {
						allFieldsFilled = false;
					} else {
						allFieldsFilled = null;
					}
				}
			}
		}

		if (allFieldsFilled != null) {
			updateSkpp(skpp, allFieldsFilled);
		}
	}

	/**
	 * Формирование Map, где ключ - уникальное значение из строк, а значение - строка плана
	 * @param skpp План СКПП
	 * @return Карта строк плана СКПП
	 */
	private Map<String, SkppStrs> mapSkppStrs(Skpp skpp) {
		return skpp.getSkppStrs().stream()
				.collect(Collectors.toMap(
						skppStrs -> generateKey(skppStrs.getClaimId(), skppStrs.getFrGgNumber(), skppStrs.getRpsId(),
								skppStrs.getStNaznCode(), skppStrs.getFrCode()),
						Function.identity()
				));
	}

	/**
	 * Получение строки плана из Map по уникальному ключу
	 * @param skppStrsMap Коллекция строк плана
	 * @param row Строка плана из Этран
	 * @return Строка плана СКПП
	 */
	private SkppStrs findSkppStrs(Map<String, SkppStrs> skppStrsMap, Row row) {
		String key = generateKey(row.getClaimId(), row.getFrGgNumber(), row.getRpsId(),
				row.getClpType().equals(1) ? row.getStNaznCode() : row.getStNaznSngCode(), row.getFrCode());
		return skppStrsMap.get(key);
	}

	/**
	 * Устанавливает значение allFieldsFilled в true, если все поля погрузки заполнены
	 * @param row строка плана полученная из Этран
	 * @return boolean
	 */
	private boolean isLoadingFieldsFilled(Row row) {
		return row.getPogrTonn() != null && row.getPogrVag() != null;
	}

	/**
	 * Устанавливает значение allFieldsFilled в false, если все поля согласования заполнены или получен отказ
	 * @param row строка плана полученная из Этран
	 * @return boolean
	 */
	private boolean isAgreedFieldsFilled(Row row) {
		boolean isSoglFilled = row.getSoglTonn() != null && row.getSoglVag() != null;
		boolean isReasonDMZIFilled = row.getReasonDMZI() != null;

		return isSoglFilled ^ isReasonDMZIFilled;
	}

	/**
	 * Обновление плана данными из Этран и выставления признака состояния. 3 если получены данные погрузки
	 * и 2 если данные только согласованны
	 * @param skpp План СКПП
	 * @param allFieldsFilled Индикатор по которому будет установлен признак
	 */
	private void updateSkpp(Skpp skpp, boolean allFieldsFilled) {
		skpp.setRespText(null);
		skpp.setIsRun(allFieldsFilled ? THREE : TWO);
	}

	/**
	 * Формирование уникального ключа строки плана СКПП.
	 *
	 * @param claimId ИД заявки
	 * @param frGroupCode Код груза
	 * @param rpsId Код рода подвижного состава
	 * @param stNaznCode Код станции назначения
	 * @return Уникальный ключ из значений уникальных полей
	 */
	private String generateKey(Integer claimId, Integer frGroupCode, Integer rpsId, String stNaznCode, String frCode) {
		return new StringBuilder(EIGHTY)
				.append(claimId)
				.append("-")
				.append(frGroupCode)
				.append("-")
				.append(rpsId)
				.append("-")
				.append(stNaznCode)
				.append("-")
				.append(frCode)
				.toString();
	}

	/**
	 * Отправляет запрос в сервис SKPP и получает ответ.
	 *
	 * @param object объект запроса, который будет отправлен в Этран
	 * @param skpp план СКПП
	 * @return корневой элемент ответа
	 */
	private Object executeRequestAndGetResponse(Object object, Skpp skpp) {
		if (login.isBlank() || password.isBlank()) {
			log.error(LOGIN_PASSWORD_ERROR);
			throw new AuthException(LOGIN_PASSWORD_ERROR);
		}

		String request = XmlUtility.marshal(object);

		Envelope envelope = client.call(SoapRequestBuilder.build(login, password, request));
		String response = envelope.getBody().getGetBlockResponse().getText();

		Object rootElement = XmlUtility.getRootElement(response);

		saveXmlQueryLog(skpp, request, response);

		return rootElement;
	}

	/**
	 * Сохраняет лог XML запроса и ответа в базу данных.
	 *
	 * @param skpp объект запроса
	 * @param request XML запрос
	 * @param response XML ответ
	 */
	private void saveXmlQueryLog(Skpp skpp, String request, String response) {
		XmlQueryLog xmlQueryLog = new XmlQueryLog();
		xmlQueryLog.setObjectType(skpp.getClass().getSimpleName());
		xmlQueryLog.setObjectId(skpp.getId());
		xmlQueryLog.setXmlRequest(request);
		xmlQueryLog.setXmlResponse(response);

		xmlQueryLogRepository.save(xmlQueryLog);
	}

}