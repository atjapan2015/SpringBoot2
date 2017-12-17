package com.springboot2.business.sample.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { WorkOverTimeValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkOverTime {
	String message() default "Too Much Over Time Work";

	int max() default 5;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
