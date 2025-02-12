package com.github.lupaev.etran.specification;

import com.github.lupaev.etran.entity.Skpp;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static com.github.lupaev.etran.util.AppConstants.*;


public final class SkppSpecification {

	private SkppSpecification() {
	}

	public static Specification<Skpp> filterSkppForGetSkpp() {
		return (root, query, cb) -> {
			Predicate isRunNotMinusTwo = cb.notEqual(root.get(IS_RUN), MINUS_TWO);
			Predicate isRunNotMinusOne = cb.notEqual(root.get(IS_RUN), MINUS_ONE);
			Predicate isRunNotZero = cb.notEqual(root.get(IS_RUN), ZERO);
			Predicate isRunNotThree = cb.notEqual(root.get(IS_RUN), THREE);
			Predicate isRunNotFour = cb.notEqual(root.get(IS_RUN), FOUR);

			LocalDateTime now = LocalDateTime.now().toLocalDate().atStartOfDay();
			LocalDateTime fiveDaysLater = now.plusDays(5);
			Predicate reportDateBetween = cb.between(root.get("reportDate"), now, fiveDaysLater);

			return cb.and(isRunNotZero, isRunNotThree, isRunNotFour, isRunNotMinusTwo, isRunNotMinusOne, reportDateBetween);
		};
	}

	public static Specification<Skpp> filterSkppForSetSkpp() {
		return (root, query, cb) -> {
			Predicate isRunNotZero = cb.equal(root.get(IS_RUN), ZERO);

			return cb.and(isRunNotZero);
		};
	}
}
