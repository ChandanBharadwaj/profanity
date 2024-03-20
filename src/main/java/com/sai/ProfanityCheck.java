package com.sai;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProfanityValidator.class)
@Target(ElementType.FIELD)
public @interface ProfanityCheck {
    String message() default "Validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // Add any attributes needed for validation
}