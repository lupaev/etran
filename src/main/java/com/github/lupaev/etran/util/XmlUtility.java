package com.github.lupaev.etran.util;

import com.github.lupaev.etran.exception.XmlProcessingException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static com.github.lupaev.etran.util.AppConstants.*;


/**
 * Класс для обработки XML
 */
@Slf4j
public final class XmlUtility {

    private static final String PACKAGE_PATH = "com.github.lupaev.etran.dto.";

    private XmlUtility() {
    }

    /**
     * Получение класса из строки ответа в формате XML
     * @param xmlContent строка в формате XML
     * @return Объект полученный по rootName XML
     */
    public static Object getRootElement(String xmlContent) {
        String name = toUpperCaseFirstChar(getRootElementName(xmlContent));

        Class<?> aClass;
        try {
            aClass = Class.forName(PACKAGE_PATH + name);
        } catch (ClassNotFoundException e) {
			log.error(ERROR_FOUND_CLASS, name);
            throw new XmlProcessingException(e.getMessage());
        }

        return unmarshal(xmlContent, aClass);
    }

    /**
     * Преобразование строчной первой буквы имени класса в заглавную
     * @param name rootName из строки XML
     * @return rootName из строки XML
     */
    private static String toUpperCaseFirstChar(String name){
        if (name != null && !name.isEmpty() && Character.isLowerCase(name.charAt(0))) {
           return Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        return name;
    }

    /**
     * Получение rootName из строки XML
     * @param xmlContent строка ответа в формате XML
     * @return rootName XML
     */
    private static String getRootElementName(String xmlContent) {
        try {
            XMLInputFactory xif = XMLInputFactory.newDefaultFactory();
            XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(xmlContent));
            while (xsr.hasNext()) {
                int eventType = xsr.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    return xsr.getLocalName();
                }
            }
        } catch (XMLStreamException e) {
			log.error(ERROR_READING_XML, e.getMessage());
            throw new XmlProcessingException(e.getMessage());
        }
        return null;
    }

    /**
     * Преобразование объекта в XML
     * @param object любой объект для преобразования
     * @return объекта в XML в формате строки
     */
    public static String marshal(Object object) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(object, new StreamResult(writer));
            return writer.toString().replaceFirst("<\\?xml.*\\?>", "").trim();
        } catch (JAXBException e) {
			log.error(ERROR_MARSHAL_XML, e.getMessage());
            throw new XmlProcessingException(e.getMessage());
        }
    }

    /**
     *
     * @param xmlString строка ответа в формате XML
     * @param clazz Класс в который будет преобразовываться строка
     * @return Объект, результат преобразования
     */
    public static <T> T unmarshal(String xmlString, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            return clazz.cast(unmarshaller.unmarshal(new StreamSource(reader)));
        } catch (JAXBException e) {
			log.error(ERROR_UNMARSHAL_XML, e.getMessage());
            throw new XmlProcessingException(e.getMessage());
        }
    }
}
