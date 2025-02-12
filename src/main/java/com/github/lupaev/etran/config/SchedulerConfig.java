package com.github.lupaev.etran.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


/**
 * Конфигурационный класс для настройки и предоставления {@link TaskScheduler}.
 * {@link TaskScheduler} используется для планирования и выполнения асинхронных задач в приложении.
 * Этот класс позволяет централизованно настроить пул потоков для планировщика задач, оптимизируя
 * использование ресурсов и управление выполнением задач в приложении.
 */
@Configuration
public class SchedulerConfig {

	@Value(value = "${pool.size}")
	private int size;

	/**
	 * Создает и возвращает настроенный экземпляр {@link TaskScheduler}.
	 * Использует {@link ThreadPoolTaskScheduler} для создания пула потоков, который будет использоваться
	 * для планирования и выполнения задач. Этот метод помечен аннотацией {@link Bean}, что указывает Spring
	 * контейнеру на необходимость создать и зарегистрировать {@link TaskScheduler} как бин в контексте приложения.
	 *
	 * @return Настроенный экземпляр {@link TaskScheduler} с заданным размером пула потоков.
	 */
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(size);
		scheduler.initialize();
		return scheduler;
	}
}
