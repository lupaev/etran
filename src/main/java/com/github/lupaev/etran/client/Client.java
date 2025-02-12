package com.github.lupaev.etran.client;


import com.github.lupaev.etran.dto.Envelope;

/**
 * Интерфейс, представляющий клиента для выполнения вызовов к Этран.
 * Определяет общий контракт для всех клиентов, позволяя им реализовывать метод {@code call},
 * который должен быть использован для выполнения запросов и получения ответов от Этран.
 */
public interface Client {

	/**
	 * Выполняет вызов к внешнему сервису и возвращает результат.
	 * Метод предназначен для реализации логики запроса к сервису и обработки ответа.
	 * @param envelope {@link Envelope} в формате строки
	 * @return {@link Envelope}
	 */
	Envelope call(String envelope);
}