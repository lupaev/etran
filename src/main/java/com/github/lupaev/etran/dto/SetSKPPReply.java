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
 * Ответ на передачу Суточного клиентского плана погрузки
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "SetSKPPReply")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetSKPPReply {

	/**
	 * Идентификатор клиента
	 */
	@XmlElement(name = "ClOrgId")
	private Integer clOrgId;

	/**
	 * Отчётная дата
	 * Формат даты: дд.мм.гггг 00:00:00
	 */
	@XmlElement(name = "ReportDate", required = true)
	private String reportDate;

	/**
	 * Результат обработки запроса. 1 - ОК
	 */
	@XmlElement(name = "Result")
	private Integer result;

	/**
	 * Предупреждение
	 */
	@XmlElement(name = "warning")
	private String warning;
}
