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
 * Причины невыполнения СКПП
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "REASON")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reason {

	/**
	 * ИД причины по НСИ clp_reason
	 * (Обязательный элемент)
	 */
	@XmlElement(name = "ID", required = true)
	private Integer id;

	/**
	 * Код причины по clp_reason
	 */
	@XmlElement(name = "CODE")
	private Integer code;

	/**
	 * Количество вагонов
	 * (Обязательный элемент)
	 */
	@XmlElement(name = "COUNT", required = true)
	private Integer count;

	/**
	 * Наименование причины
	 */
	@XmlElement(name = "NAME")
	private String name;

	/**
	 * ИД группы ответственности
	 */
	@XmlElement(name = "GROUP_REASON_ID")
	private Integer groupReasonId;

	/**
	 * Наименование группы ответственности
	 */
	@XmlElement(name = "GROUP_REASON_NAME")
	private String groupReasonName;
}
