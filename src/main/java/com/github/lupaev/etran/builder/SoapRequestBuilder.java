package com.github.lupaev.etran.builder;

/**
 * Форма для запроса
 */
public final class SoapRequestBuilder {
	private SoapRequestBuilder() {
	}

	private static final String SOAP_TEMPLATE = """
    <?xml version='1.0' encoding='UTF-8'?>
    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                       xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        <SOAP-ENV:Body>
            <ns1:GetBlock xmlns:ns1="SysEtranInt" SOAP-ENV:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
                <Login>%s</Login>
                <Password>%s</Password>
                <Text>%s</Text>
            </ns1:GetBlock>
        </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
    """;

	public static String build(String login, String password, String textXml) {
		return String.format(SOAP_TEMPLATE, login, password, textXml);
	}
}
