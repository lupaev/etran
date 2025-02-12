package com.github.lupaev.etran.dto;

import lombok.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Строка отправки для передачи СКПП
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "RowSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class RowSet {

	/**
	 * Признак выполняемого действия со строкой СКПП.
	 * 1 - вставка новой строки
	 * 2 - обновление строки (по clp_stngg_id)
	 * 3 - удаление строки (по clp_stngg_id)
	 */
	@XmlElement(name = "Action")
	private Integer action;

	/**
	 * Идентификатор строки
	 */
	@XmlElement(name = "clp_stngg_id")
	private Integer clpStnggId;

	/**
	 * Идентификатор ГУ-12
	 */
	@XmlElement(name = "claim_id")
	private Integer claimId;

	/**
	 * Прогноз СКПП (тонн)
	 */
	@XmlElement(name = "plan_tonn", required = true)
	private Integer planTonn;

	/**
	 * Прогноз СКПП (вагоны)
	 */
	@XmlElement(name = "plan_vag", required = true)
	private Integer planVag;

	/**
	 * Причины невыполнения СКПП
	 */
	@XmlElementWrapper(name = "REASONS")
	@XmlElement(name = "REASON")
	private List<Reason> reasons;

	/**
	 * Код дороги назначения/выхода из РФ (или СНГ).
	 * Для вида сообщение прямое и импорт - дорога назначения РФ
	 * Для вида сообщения экспорт - дорога выхода из РФ или СНГ
	 */
	@XmlElement(name = "rw_nazn_code", required = true)
	private String rwNaznCode;

	/**
	 * Код станции отправления
	 */
	@XmlElement(name = "st_otp_code", required = true)
	private String stOtpCode;

	/**
	 * Код станции назначения СНГ
	 */
	@XmlElement(name = "st_nazn_sng_code")
	private String stNaznSngCode;

	/**
	 * Группа груза (GG_NUMBER по НСИ SUM_FREIGHT)
	 */
	@XmlElement(name = "fr_gg_number", required = true)
	private Integer frGgNumber;

	/**
	 * Код груза ЕТСНГ
	 */
	@XmlElement(name = "fr_code")
	private String frCode;

	/**
	 * Код станции назначения/выхода из РФ
	 * Для вида сообщение прямое - станция назначения
	 * Для вида сообщения экспорт - станция выхода из РФ
	 */
	@XmlElement(name = "st_nazn_code")
	private String stNaznCode;

	/**
	 * ИД паспорта (orgPassport) пункта перевалки
	 * Указывается в случае выхода из РФ через Порт
	 */
	@XmlElement(name = "ts_id")
	private Integer tsId;

	/**
	 * Род подвижного состава - ИД по НСИ KIND_CAR_SVOD
	 */
	@XmlElement(name = "rps_id", required = true)
	private Integer rpsId;

	/**
	 * ИД паспорта (orgPassport) собственника вагона
	 */
	@XmlElement(name = "WagOwner_id")
	private Integer wagOwnerId;

	/**
	 * Вид сообщения.
	 * 1 - Прямое
	 * 2 - Экспорт
	 * 3 - Импорт
	 */
	@XmlElement(name = "clp_type", required = true)
	private Integer clpType;

	/**
	 * Доп. услуга
	 */
	@XmlElement(name = "AuxServ")
	private AuxServ auxServ;

	/**
	 * Номер договора на организацию перевозок грузов по графику.
	 * Передается в случае указания по строке СКПП вида дополнительной
	 * услуги «КП» (контейнерный поезд) или «Д» (договорной поезд).
	 */
	@XmlElement(name = "transCntrNum")
	private String transCntrNum;

	/**
	 * Идентификатор паспорта грузополучателя (orgPassport)
	 */
	@XmlElement(name = "RecipOrgId")
	private Integer recipOrgId;

	/**
	 * Флаг согласия на автоматический перенос несогласованных ОАО «РЖД» объемов на следующие сутки
	 * 1 - выполнить перенос
	 * 0 - не выполнять перенос ( по умолчанию).
	 * При указании значения 1 выполняется автоматический перенос строки СКПП с несогласованными ОАО «РЖД» объемами
	 * на следующие сутки».
	 */
	@XmlElement(name = "ExtraNextDay")
	private Integer extraNextDay;

	/**
	 * Количество вагонов, планируемое к погрузке с 18:00 до 24:00 мск времени, ваг
	 */
	@XmlElement(name = "PLAN_WAG_1824INTERV")
	private Integer planWag1824Interv;

	/**
	 * Идентификатор паспорта плательщика (orgPassport)
	 */
	@XmlElement(name = "PayerOrgID")
	private Integer payerOrgID;

}
