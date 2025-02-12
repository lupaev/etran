package com.github.lupaev.etran.service;

public interface SkppService {

	/**
	 * Отправка данных в Этран для обработки
	 */
	void processSetBlock();

	/**
	 * Получение результатов обработки
	 */
	void processGetBlock();
}
