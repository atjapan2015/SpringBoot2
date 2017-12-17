package com.springboot2.business.sample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkOverTimeValidator implements ConstraintValidator<WorkOverTime, Integer> {

	WorkOverTime work;
	int max;

	@Override
	public void initialize(WorkOverTime work) {
		this.work = work;
		max = work.max();

	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		return value < max;
	}

}
