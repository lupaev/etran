package com.github.lupaev.etran.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Конфигурационный класс для создания и предоставления бина {@link RestClient}.
 * {@link RestClient} используется для выполнения HTTP-запросов и обработки HTTP-ответов.
 * Этот класс обеспечивает централизованное место для конфигурации {@link RestClient} в приложении.
 */
@Configuration
public class RestClientConfig {

	/**
	 * Создает и возвращает экземпляр {@link RestClient}.
	 * @return Новый экземпляр {@link RestClient}, готовый к использованию для выполнения HTTP-запросов.
	 */
	@Bean
	public RestClient restClient(RestTemplateBuilder restTemplateBuilder) {
		return RestClient.create(restTemplateBuilder
				.additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("Windows-1251")),
						new StringHttpMessageConverter(StandardCharsets.UTF_8)).build());
	}
}