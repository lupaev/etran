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
 * Ответ на запрос Суточного клиентского плана погрузки
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "GetSKPPReply")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSKPPReply {

	/**
	 * Данные Суточного клиентского плана погрузки по организации
	 */
	@XmlElement(name = "ClientRow")
	private List<ClientRow> clientRows;
}
