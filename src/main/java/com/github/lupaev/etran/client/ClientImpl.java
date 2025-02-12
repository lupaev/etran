package com.github.lupaev.etran.client;

import com.github.lupaev.etran.dto.Envelope;
import com.github.lupaev.etran.util.XmlUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;


/**
 * Клиент для взаимодействия с Этран.
 */
@Slf4j
@Component("clientImpl")
@RequiredArgsConstructor
public class ClientImpl implements Client {

	private final RestClient restClient;

	@Value("${etran.url}")
	private String url;

	/**
	 * Отправляет SOAP-запрос и получает ответ.
	 *
	 * @param envelope SOAP-запрос в виде строки
	 * @return объект Envelope, представляющий ответ
	 */
	@Override
	public Envelope call(String envelope) {
		String response;
		try {
			response = restClient.post()
				.uri(url)
				.contentType(MediaType.TEXT_XML)
				.acceptCharset(StandardCharsets.UTF_8)
				.body(envelope)
				.retrieve()
				.body(String.class);
		} catch (Exception e) {
			log.error("Ошибка при отправке запроса в URL: {}", url, e);
			throw e;
		}

		Envelope envelopeResponse;
		try {
			envelopeResponse = XmlUtility.unmarshal(response, Envelope.class);
		} catch (Exception e) {
			log.error("Ошибка при анмаршалинге ответа: {}", response, e);
			throw e;
		}

		return envelopeResponse;
	}
}
