package com.github.lupaev.etran.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ошибка запроса этран
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error {

	@XmlAttribute(name = "version")
	private String version;

	@XmlElement(name = "errorCode")
	private ErrorDetail errorCode;

	@XmlElement(name = "errorMessage")
	private ErrorDetail errorMessage;

	@XmlElement(name = "errorStatusCode")
	private ErrorDetail errorStatusCode;

	@Getter
	@Setter
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ErrorDetail {
		@XmlAttribute(name = "value")
		private String value;
	}
}
