package com.tms.annotation;

import com.tms.utils.FirstCharacter8Logic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = FirstCharacter8Logic.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstCharacter8 {

    String message() default "First character not +";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
