package com.github.lupaev.etran;

import com.github.lupaev.etran.service.SkppService;
import com.github.lupaev.etran.service.SkppServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class TaskRunner {

	private final SkppService skppService;

	/**
	 * Запускает процесс обработки результатов через {@link SkppServiceImpl}.
	 * Выполнение происходит с фиксированным интервалом, определенным в настройках.
	 */
	@Scheduled(timeUnit = TimeUnit.MINUTES, fixedRateString = "${delay.time}")
	private void run() {
		try {
			skppService.processSetBlock();
			skppService.processGetBlock();
		} catch (Exception error) {
			log.error("Ошибка при выполнении задачи", error);
		}
	}
}
