package com.github.lupaev.etran.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

/**
 * Передача Суточного клиентского плана погрузки
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "SetSKPP")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetSKPP {

	/**
	 * Отчётная дата
	 */
	@XmlElement(name = "ReportDate", required = true)
	private String reportDate;

	/**
	 * Дата окончания периода заполнения СКПП по графику подач. Начало периода заполнения – дата из поля ReportDate
	 * «Отчётная дата». Требуется заполнение, если необходимо заполнить СКПП по графику подач на период. Поле
	 * заполняется при указании флага расчета по графику подач autocalc_flag = 1
	 */
	@XmlElement(name = "ReportDateEnd")
	private String reportDateEnd;

	/**
	 * Идентификатор клиента. Значения по умолчанию - ИД организации пользователя, выполняющго запрос
	 */
	@XmlElement(name = "ClOrgId")
	private Integer clOrgId;

	/**
	 * Строка отправки для передачи СКПП
	 */
	@XmlElement(name = "RowSet", required = true)
	private List<RowSet> rowSets;

	/**
	 * Флаг автоматического расчёта по данным графика подачи.
	 * 1 - выполнить расчёт
	 * 0 - не выполнять.
	 * При указании значения 1 выполняется заполнение СКПП данными, рассчитанными в соответствии с ГУ-12,
	 * переданные значения в RowSet игнорируются.
	 * Значение по умолчанию - 0
	 */
	@XmlElement(name = "autocalc_flag")
	private Integer autocalcFlag;


}
