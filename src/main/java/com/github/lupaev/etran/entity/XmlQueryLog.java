package com.github.lupaev.etran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "XML_QUERY_LOG")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class XmlQueryLog {

	/**
	 * Первичный ключ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Наименование объекта запрос которого будет сохранен
	 */
	@Column(name = "OBJECT_TYPE")
	private String objectType;

	/**
	 * Ид объекта запрос которого будет сохранен
	 */
	@Column(name = "OBJECT_ID")
	private Long objectId;

	/**
	 * XML запрос в ЭТРАН
	 */
	@Column(name = "XML_REQUEST")
	private String xmlRequest;

	/**
	 * XML ответ из ЭТРАН
	 */
	@Column(name = "XML_RESPONSE")
	private String xmlResponse;
}
