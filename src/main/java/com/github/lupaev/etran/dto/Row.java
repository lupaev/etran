package com.github.lupaev.etran.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Данные Суточного клиентского плана погрузки
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ROW")
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {

	/**
	 * Порядковый номер строки
	 */
	@XmlElement(name = "NUMPP")
	private Integer numpp;

	/**
	 * Идентификатор строки
	 */
	@XmlElement(name = "CLP_STNGG_ID")
	private Integer clpStnggId;

	/**
	 * Идентификатор ГУ-12
	 */
	@XmlElement(name = "CLAIM_ID")
	private Integer claimId;

	/**
	 * Номер ГУ-12
	 */
	@XmlElement(name = "CLAIM_NUM")
	private String claimNum;

	/**
	 * Код дороги отправления
	 */
	@XmlElement(name = "RW_OTP_CODE")
	private String rwOtpCode;

	/**
	 * Наименование дороги отправления
	 */
	@XmlElement(name = "RW_OTP_NAME")
	private String rwOtpName;

	/**
	 * Код станции отправления
	 */
	@XmlElement(name = "ST_OTP_CODE")
	private String stOtpCode;

	/**
	 * Наименование станции отправления
	 */
	@XmlElement(name = "ST_OTP_NAME")
	private String stOtpName;

	/**
	 * Наименование группы груза
	 */
	@XmlElement(name = "FR_GROUP_NAME")
	private String frGroupName;

	/**
	 * Группа груза (GG_NUMBER по НСИ SUM_FREIGHT)
	 */
	@XmlElement(name = "FR_GG_NUMBER")
	private Integer frGgNumber;

	/**
	 * Наименование груза
	 */
	@XmlElement(name = "FR_NAME")
	private String frName;

	/**
	 * Код груза ЕТСНГ
	 */
	@XmlElement(name = "FR_CODE")
	private String frCode;

	/**
	 * Состояние заявки ГУ-12
	 */
	@XmlElement(name = "STATE")
	private Integer state;

	/**
	 * Состояние заявки ГУ-12 - наименование
	 */
	@XmlElement(name = "STATE_NAME")
	private String stateName;

	/**
	 * Код дороги назначения/выхода из РФ (или СНГ)
	 */
	@XmlElement(name = "RW_NAZN_CODE")
	private String rwNaznCode;

	/**
	 * Наименование дороги назначения/выхода из РФ (или СНГ)
	 */
	@XmlElement(name = "RW_NAZN_NAME")
	private String rwNaznName;

	/**
	 * Код станции назначения РФ/выхода из РФ
	 */
	@XmlElement(name = "ST_NAZN_CODE")
	private String stNaznCode;

	/**
	 * Наименование станции назначения СНГ
	 */
	@XmlElement(name = "ST_NAZN_SNG_NAME")
	private String stNaznSngName;

	/**
	 * Код станции назначения СНГ
	 */
	@XmlElement(name = "ST_NAZN_SNG_CODE")
	private String stNaznSngCode;

	/**
	 * Наименование станции назначения РФ/выхода из РФ
	 */
	@XmlElement(name = "ST_NAZN_NAME")
	private String stNaznName;

	/**
	 * ИД паспорта (orgPassport) пункта перевалки
	 */
	@XmlElement(name = "TS_ID")
	private Integer tsId;

	/**
	 * Наименование пункта перевалки
	 */
	@XmlElement(name = "TS_NAME")
	private String tsName;

	/**
	 * Прогноз СКПП (тонн)
	 */
	@XmlElement(name = "PLAN_TONN")
	private Integer planTonn;

	/**
	 * Прогноз СКПП (вагоны)
	 */
	@XmlElement(name = "PLAN_VAG")
	private Integer planVag;

	/**
	 * Тонны по ГУ-12
	 */
	@XmlElement(name = "CLAIM_TONN")
	private Integer claimTonn;

	/**
	 * Вагоны по ГУ-12
	 */
	@XmlElement(name = "CLAIM_VAG")
	private Integer claimVag;

	/**
	 * Остаток вагонов по ГУ-12
	 */
	@XmlElement(name = "CLAIM_OST")
	private Integer claimOst;

	/**
	 * Погружено фактически в отчётные сутки, тонн
	 */
	@XmlElement(name = "POGR_TONN")
	private Double pogrTonn;

	/**
	 * Погружено фактически в отчётные сутки, вагонов
	 */
	@XmlElement(name = "POGR_VAG")
	private Integer pogrVag;

	/**
	 * Наименование рода подвижного состава
	 */
	@XmlElement(name = "RPS_NAME")
	private String rpsName;

	/**
	 * Род подвижного состава - ИД по НСИ KIND_CAR_SVOD
	 */
	@XmlElement(name = "RPS_ID")
	private Integer rpsId;

	/**
	 * ИД паспорта (orgPassport) собственника вагона
	 */
	@XmlElement(name = "WAGOWNER_ID")
	private Integer wagOwnerId;

	/**
	 * Наименование собственника вагона
	 */
	@XmlElement(name = "WAGOWNER_NAME")
	private String wagOwnerName;

	/**
	 * Отклонение факта к согласованному плану, вагонов
	 */
	@XmlElement(name = "DELTA_VAG")
	private Integer deltaVag;

	/**
	 * Вид сообщения. 1 - Прямое, 2 - Экспорт, 3 - Импорт
	 */
	@XmlElement(name = "CLP_TYPE")
	private Integer clpType;

	/**
	 * Согласовано СКПП, вагонов
	 */
	@XmlElement(name = "SOGL_VAG")
	private Integer soglVag;

	/**
	 * Согласовано портом, вагонов
	 */
	@XmlElement(name = "SOGL_VAG_PORT")
	private Integer soglVagPort;

	/**
	 * Признак попадания под лимитирующее направление. 1 - Да, 0 - Нет
	 */
	@XmlElement(name = "LIMSIGN")
	private Integer limSign;

	/**
	 * Признак наличия истории у записи. 1 - Да, 0 - Нет
	 */
	@XmlElement(name = "HistSign")
	private Integer histSign;

	/**
	 * Причины невыполнения СКПП
	 */
	@XmlElementWrapper(name = "REASONS")
	@XmlElement(name = "REASON")
	private List<Reason> reasons;

	/**
	 * Доп. услуга
	 */
	@XmlElement(name = "AuxServ")
	private AuxServ auxServ;

	/**
	 * Номер договора на организацию перевозок грузов по графику
	 */
	@XmlElement(name = "transCntrNum")
	private String transCntrNum;

	/**
	 * Код рода подвижного состава, KOD_SVOD_RV по НСИ KIND_CAR_SVOD
	 */
	@XmlElement(name = "RPS_CODE")
	private String rpsCode;

	/**
	 * Согласовано СКПП, тонн
	 */
	@XmlElement(name = "SOGL_TONN")
	private Integer soglTonn;

	/**
	 * Идентификатор паспорта грузополучателя (orgPassport)
	 */
	@XmlElement(name = "RECIP_ORG_ID")
	private Integer recipOrgId;

	/**
	 * ОКПО грузополучателя
	 */
	@XmlElement(name = "RECIP_OKPO")
	private String recipOkpo;

	/**
	 * Наименование грузополучателя
	 */
	@XmlElement(name = "RECIP_NAME")
	private String recipName;

	/**
	 * Количество вагонов, планируемое к погрузке с 18:00 до 24:00 мск времени, ваг
	 */
	@XmlElement(name = "PLAN_WAG_1824INTERV")
	private Integer planWag1824Interv;

	/**
	 * Погружено с 18:00 до 24:00 мск времени, ваг
	 */
	@XmlElement(name = "POGR_WAG_1824INTERV")
	private Integer pogrWag1824Interv;

	/**
	 * Погружено с 18:00 до 24:00 мск времени, тонн
	 */
	@XmlElement(name = "POGR_TONN_1824INTERV")
	private Integer pogrTonn1824Interv;

	/**
	 * Причина несогласования ДМ ЗИ
	 */
	@XmlElement(name = "ReasonDMZI")
	private String reasonDMZI;

	/**
	 * Согласовано грузополучателем, вагонов
	 */
	@XmlElement(name = "SOGL_VAG_RECIPIENT")
	private Integer soglVagRecipient;

	/**
	 * Идентификатор паспорта плательщика (orgPassport)
	 */
	@XmlElement(name = "PayerOrgID")
	private Integer payerOrgID;

	/**
	 * ОКПО плательщика
	 */
	@XmlElement(name = "PayerOKPO")
	private String payerOkpo;

	/**
	 * Наименование плательщика
	 */
	@XmlElement(name = "PayerName")
	private String payerName;

	/**
	 * Признаки назначения из заявки на грузоперевозку
	 */
	@XmlElementWrapper(name = "TypeDelivs")
	@XmlElement(name = "TypeDeliv")
	private List<TypeDeliv> typeDelivs;
}
