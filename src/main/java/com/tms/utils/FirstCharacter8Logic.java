package com.tms.utils;

import com.tms.annotation.FirstCharacter8;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstCharacter8Logic implements ConstraintValidator<FirstCharacter8,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("8");
    }
}
