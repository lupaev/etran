package com.github.lupaev.etran.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Запрос Суточного клиентского плана погрузки
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "GetSKPP")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSKPP {

	/**
	 * Отчётная дата
	 */
	@XmlElement(name = "ReportDate", required = true)
	private String reportDate;

	/**
	 * Идентификатор клиента. Значения по умолчанию - ИД организации пользователя, выполняющго запрос.
	 */
	@XmlElement(name = "ClOrgId")
	private Integer clOrgId;

	/**
	 * Флаг запроса списка действующих невыполненных заявок ГУ-12 Клиента.
	 */
	@XmlElement(name = "GetClaimList")
	private Boolean getClaimList;

	/**
	 * Получение 6-значных кодов в ответе
	 */
	@XmlElement(name = "useMod11")
	private Boolean useMod11;

	/**
	 * Код дороги (RW_CODE по НСИ RAILWAY). Возвращать информацию только со строками СКПП с данной дорогой отправления.
	 */
	@XmlElement(name = "RW_OTP_CODE")
	private String rwOtpCode;

	/**
	 * Получать данные как собственник вагона.
	 */
	@XmlElement(name = "useTenantCar")
	private Boolean useTenantCar;

	/**
	 * Идентификатор организации грузоотправителя.
	 */
	@XmlElement(name = "SenderID")
	private Integer senderId;

	/**
	 * При указании флага будут сформированы данные по СКПП, где организация указана в качестве грузополучателя
	 */
	@XmlElement(name = "useRecip")
	private Boolean useRecip;

	/**
	 * Флаг получения строк СКПП плательщиком.
	 */
	@XmlElement(name = "usePayer")
	private Boolean usePayer;
}
