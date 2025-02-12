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
 * Общий класс для формирования запроса/ответа в этран
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlAccessorType(XmlAccessType.FIELD)
public class Envelope {

	@XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
	private SoapBody body;

	@Getter
	@Setter
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class SoapBody {

		@XmlElement(name = "GetBlockResponse", namespace = "SysEtranInt")
		private GetBlockResponse getBlockResponse;

		@Getter
		@Setter
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class GetBlockResponse {

			@XmlElement(name = "return")
			private boolean returnValue;

			@XmlElement(name = "Text")
			private String text;
		}
	}
}
