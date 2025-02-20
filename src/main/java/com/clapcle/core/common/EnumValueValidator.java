package com.clapcle.core.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class EnumValueValidator implements ConstraintValidator<EnumCore, String> {
    private EnumCore annotation;

    @Override
    public void initialize(EnumCore annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (valueForValidation.equals(enumValue.toString())
                    || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}