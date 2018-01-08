package com.springboot2.common.validator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommonValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return getTClass().isAssignableFrom(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, null);
    }

    public void validate(Object target, Errors errors, Class<?> group) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        javax.validation.Validator validator = factory.getValidator();

        T form = (T) target;

        Set<ConstraintViolation<T>> result;

        if (null == group) {
            result = validator.validate(form);
        } else {
            result = validator.validate(form, group);
        }

        if (result != null && result.size() > 0) {

            Class<T> clazz = (Class<T>) target.getClass();
            Field[] fieldArray = clazz.getDeclaredFields();

            for (Field field : fieldArray) {
                String filedName = field.getName();
                for (ConstraintViolation<T> constraintViolation : result) {

                    String msgField = constraintViolation.getPropertyPath().toString();
                    if (filedName.equals(msgField)) {
                        String msgCodeWithSign = constraintViolation.getMessageTemplate();
                        String msgCode = msgCodeWithSign;

                        if (msgCodeWithSign.contains("\"")) {
                            msgCode = StringUtils.substring(msgCodeWithSign, 1, msgCodeWithSign.length() - 1);
                        }
                        String msgDefault = constraintViolation.getMessage();
                        errors.rejectValue(msgField, msgCode, null, msgDefault);
                        return;
                    }
                }
            }
        }
    }

    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
