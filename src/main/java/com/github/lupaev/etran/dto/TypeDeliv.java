package com.github.lupaev.etran.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

/**
 * Признаки назначения из заявки на грузоперевозку
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "TypeDeliv")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeDeliv {
    /**
     * Признак назначения (SIGN_ID по НСИ DESTINATION_SIGN- )
     */
    private String value;
}
