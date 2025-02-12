package com.github.lupaev.etran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Строки суточного клиентского плана погрузки
 */
@Entity
@Table(name = "SKPP_STRS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkppStrs {

	/**
	 * Первичный ключ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Ссылка на skpp.id
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "SKPP_ID")
	private Skpp skpp;

	/**
	 * Идентификатор строки плана в ЭТРАН (получаем в ответе GetSKPP)
	 */
	@Column(name = "CLP_STRING_ID")
	private Integer clpStringId;

	/**
	 * Идентификатор заявки ГУ-12
	 */
	@Column(name = "CLAIM_ID")
	private Integer claimId;

	/**
	 * Идентификатор грузополучателя в ЭТРАН
	 */
	@Column(name = "RECIP_ORG_ID")
	private Integer recipOrgId;

	/**
	 * Наименование грузополучателя
	 */
	@Column(name = "RECIP_NAME")
	private String recipName;

	/**
	 * Тип сообщения
	 */
	@Column(name = "CLP_TYPE")
	private Integer clpType;

	/**
	 * Код дороги отправления
	 */
	@Column(name = "RW_OTP_CODE")
	private String rwOtpCode;

	/**
	 * Наименование дороги отправления
	 */
	@Column(name = "RW_OTP_NAME")
	private String rwOtpName;

	/**
	 * Код станции отправления
	 */
	@Column(name = "ST_OTP_CODE")
	private String stOtpCode;

	/**
	 * Наименование станции отправления
	 */
	@Column(name = "ST_OTP_NAME")
	private String stOtpName;

	/**
	 * Группа груза (GG_NUMBER по НСИ SUM_FREIGHT)
	 */
	@Column(name = "FR_GG_NUMBER")
	private Integer frGgNumber;

	/**
	 * Наименование группы груза
	 */
	@Column(name = "FR_GROUP_NAME")
	private String frGroupName;

	/**
	 * Код дороги назначения
	 */
	@Column(name = "RW_NAZN_CODE")
	private String rwNaznCode;

	/**
	 * Наименование дороги назначения
	 */
	@Column(name = "RW_NAZN_NAME")
	private String rwNaznName;

	/**
	 * Код станции назначения
	 */
	@Column(name = "ST_NAZN_CODE")
	private String stNaznCode;

	/**
	 * Наименование станции назначения
	 */
	@Column(name = "ST_NAZN_NAME")
	private String stNaznName;

	/**
	 * Плановое количество тонн
	 */
	@Column(name = "PLAN_TONN")
	private Integer planTonn;

	/**
	 * Плановое количество вагонов
	 */
	@Column(name = "PLAN_VAG")
	private Integer planVag;

	/**
	 * Тонны по ГУ-12
	 */
	@Column(name = "CLAIM_TONN")
	private Integer claimTonn;

	/**
	 * Вагоны по ГУ-12
	 */
	@Column(name = "CLAIM_VAG")
	private Integer claimVag;

	/**
	 * Погружено фактически в отчетные сутки, тонн
	 */
	@Column(name = "POGR_TONN")
	private Double pogrTonn;

	/**
	 * Погружено фактически в отчетные сутки, вагонов
	 */
	@Column(name = "POGR_VAG")
	private Integer pogrVag;

	/**
	 * Наименование рода подвижного состава
	 */
	@Column(name = "RPS_NAME")
	private String rpsName;

	/**
	 * Идентификатор рода ПС – укрупненный код
	 */
	@Column(name = "RPS_ID")
	private Integer rpsId;

	/**
	 * Согласовано к отправке в отчетные сутки, вагонов
	 */
	@Column(name = "SOGL_VAG")
	private Integer soglVag;

	/**
	 * Согласовано к отправке в отчетные сутки, тонн
	 */
	@Column(name = "SOGL_TONN")
	private Integer soglTonn;

	/**
	 * Согласовано портом, вагонов
	 */
	@Column(name = "SOGL_VAG_PORT")
	private Integer soglVagPort;

	/**
	 * Согласовано грузополучателем, вагонов
	 */
	@Column(name = "SOGL_VAG_RECIPIENT")
	private Integer soglVagRecipient;

	/**
	 * Причина несогласования
	 */
	@Column(name = "REASON_DMZI")
	private String reasonDmzi;

	/**
	 * Дата создания записи
	 */
	@Column(name = "CREATE_DATE")
	private LocalDate createDate;

	/**
	 * Дата последнего изменения записи
	 */
	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;

	/**
	 * Внешний id из test_zsmk.evz002_skpp_strs.id
	 */
	@Column(name = "EXT_ID")
	private Integer extId;

	/**
	 * Порт (пункт перевалки) (ссылка на NSI007_PORT.ETRAN_ID)
	 */
	@Column(name = "TS_ID")
	private Integer tsId;

	/**
	 * Наименование пункта перевалки
	 */
	@Column(name = "TS_NAME")
	private String tsName;

	/**
	 * Порядковый номер строки
	 */
	@Column(name = "NUMPP")
	private Integer numpp;

	/**
	 * Номер ГУ-12
	 */
	@Column(name = "CLAIM_NUM")
	private String claimNum;

	/**
	 * Состояние заявки ГУ-12
	 */
	@Column(name = "STATE")
	private Integer state;

	/**
	 * Состояние заявки ГУ-12 - наименование
	 */
	@Column(name = "STATE_NAME")
	private String stateName;

	/**
	 * Остаток вагонов по ГУ-12
	 */
	@Column(name = "CLAIM_OST")
	private Integer claimOst;

	/**
	 * Наименование станции назначения СНГ
	 */
	@Column(name = "ST_NAZN_SNG_NAME")
	private String stNaznSngName;

	/**
	 * Наименование груза
	 */
	@Column(name = "FR_NAME")
	private String frName;

	/**
	 * Код груза ЕТСНГ
	 */
	@Column(name = "FR_CODE")
	private String frCode;

	/**
	 * Код станции назначения СНГ
	 */
	@Column(name = "ST_NAZN_SNG_CODE")
	private String stNaznSngCode;

	/**
	 * Код рода подвижного состава, KOD_SVOD_RV по НСИ KIND_CAR_SVOD
	 */
	@Column(name = "RPS_CODE")
	private String rpsCode;

	/**
	 * ИД паспорта (orgPassport) собственника вагона
	 */
	@Column(name = "WAG_OWNER_ID")
	private Integer wagOwnerId;

	/**
	 * Наименование собственника вагона
	 */
	@Column(name = "WAG_OWNER_NAME")
	private String wagOwnerName;

	/**
	 * Номер договора на организацию перевозок грузов по графику
	 */
	@Column(name = "TRANS_CNTR_NUM")
	private String transCntrNum;

	/**
	 * ОКПО грузополучателя
	 */
	@Column(name = "RECIP_OKPO")
	private String recipOkpo;

	/**
	 * Флаг согласия на автоматический перенос несогласованных ОАО «РЖД» объемов на следующие сутки
	 * 1 - выполнить перенос
	 * 0 - не выполнять перенос ( по умолчанию).
	 * При указании значения 1 выполняется автоматический перенос строки СКПП с несогласованными ОАО «РЖД» объемами
	 * на следующие сутки».
	 */
	@Column(name = "EXTRA_NEXT_DAY")
	private Integer extraNextDay;

	/**
	 * Количество вагонов, планируемое к погрузке с 18:00 до 24:00 мск времени, ваг
	 */
	@Column(name = "PLAN_WAG1824_INTERV")
	private Integer planWag1824Interv;

	/**
	 * Идентификатор паспорта плательщика (orgPassport)
	 */
	@Column(name = "PAYER_ORG_ID")
	private Integer payerOrgID;

	/**
	 * ОКПО плательщика
	 */
	@Column(name = "PAYER_OKPO")
	private String payerOkpo;

	/**
	 * Наименование плательщика
	 */
	@Column(name = "PAYER_NAME")
	private String payerName;

	/**
	 * Отклонение факта к согласованному плану, вагонов
	 */
	@Column(name = "DELTA_VAG")
	private Integer deltaVag;

	/**
	 * Погружено с 18:00 до 24:00 мск времени, ваг
	 */
	@Column(name = "POGR_WAG1824_INTERV")
	private Integer pogrWag1824Interv;

	/**
	 * Погружено с 18:00 до 24:00 мск времени, тонн
	 */
	@Column(name = "POGR_TONN1824_INTERV")
	private Integer pogrTonn1824Interv;

	/**
	 * Признак попадания под лимитирующее направление. 1 - Да, 0 - Нет
	 */
	@Column(name = "LIM_SIGN")
	private Integer limSign;

	/**
	 * Признак наличия истории у записи. 1 - Да, 0 - Нет
	 */
	@Column(name = "HIST_SIGN")
	private Integer histSign;


}
