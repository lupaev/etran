package com.github.lupaev.etran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Очередь: Суточный клиентский план погрузки
 */
@Entity
@Table(name = "SKPP")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Skpp {

	/**
	 * Первичный ключ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Идентификатор организации в ЭТРАН. Всегда 43403.
	 */
	@Column(name = "CL_ORG_ID")
	private Integer clOrgId;

	/**
	 * Дата СКПП
	 */
	@Column(name = "REPORT_DATE")
	private LocalDate reportDate;

	/**
	 * Код станции отправления
	 */
	@Column(name = "ST_OTPR_CODE")
	private String stOtprCode;

	/**
	 * 'Дата и время отправки Запроса данных по СКПП в ЭТРАН
	 */
	@Column(name = "DATE_SENT")
	private LocalDateTime dateSent;

	/**
	 * Дата получения заполненного (или частично заполненного) ответа ЭТРАН на запрос getSKРР
	 */
	@Column(name = "DATE_ACCEPT")
	private LocalDateTime dateAccept;

	/**
	 * Дата создания записи
	 */
	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;

	/**
	 * Дата последнего изменения записи
	 */
	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;

	/**
	 * Внешний id из test_zsmk.evz001_skpp.id
	 */
	@Column(name = "EXT_ID")
	private Integer extId;

	/**
	 * (0) - запись готова к обработке,
	 * (1) - запись обработана,
	 * (2) - получен ответ из ЭТРАН,
	 * (-1) - ошибка передачи,
	 * (-2) - ошибка получения ответа
	 */
	@Column(name = "IS_RUN")
	private Integer isRun;

	/**
	 * Текст ответа ЭТРАН
	 */
	@Column(name = "RESP_TEXT")
	private String respText;

	/**
	 * Строки суточного клиентского плана погрузки
	 */
	@OneToMany(mappedBy = "skpp", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SkppStrs> skppStrs;
}
