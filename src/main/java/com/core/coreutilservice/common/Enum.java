package com.core.coreutilservice.common;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EnumValueValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    enum Y_N {
        Y("Y"),
        N("N");


        private final String value;

        Y_N(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum MINOR_MAJOR {
        MINOR("MINOR"),
        MAJOR("MAJOR");
        private final String value;
        MINOR_MAJOR(String value) {this.value=value;}
        public String getValue() {return value;}


    }

    String message() default "Invalid value!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

    boolean ignoreCase() default false;
}
