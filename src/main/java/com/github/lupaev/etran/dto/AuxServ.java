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
 * Доп. услуга
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "AuxServ")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuxServ {

	/**
	 * ИД услуги по НСИ AUX_SERVICES
	 */
	@XmlElement(name = "Aux_ID", type = Integer.class, required = true)
	private Integer auxId;

	/**
	 * Наименование
	 */
	@XmlElement(name = "Name", type = String.class)
	private String name;
}
