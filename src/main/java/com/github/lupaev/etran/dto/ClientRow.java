package com.github.lupaev.etran.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Данные Суточного клиентского плана погрузки по организации
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ClientRow")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientRow {

	/**
	 * Идентификатор клиента
	 */
	@XmlElement(name = "ClOrgId", required = true)
	private Integer clOrgId;

	/**
	 * ОКПО клиента
	 */
	@XmlElement(name = "ClOrgOKPO", required = true)
	private String clOrgOKPO;

	/**
	 * Наименование клиента
	 */
	@XmlElement(name = "ClOrgName", required = true)
	private String clOrgName;

	/**
	 * Отчётная дата
	 * Формат даты: дд.мм.гггг 00:00:00
	 */
	@XmlElement(name = "ReportDate", required = true)
	private String reportDate;

	/**
	 * Строки данных
	 */
	@XmlElement(name = "ROW")
	private List<Row> rows;

	/**
	 * Признак возможности редактирования плана на момент запроса
	 * 1 - редактирование возможно
	 * 0 - редактирование не возможно
	 */
	@XmlElement(name = "Rights_Flag")
	private Integer rightsFlag;
}
