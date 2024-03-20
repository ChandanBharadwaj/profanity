package com.sai;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProfanityValidator implements ConstraintValidator<ProfanityCheck, String> {

    @Override
    public void initialize(ProfanityCheck constraintAnnotation) {
        // Initialization logic if needed
    	System.out.println("helloinit");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	System.out.println("Validation="+value);
    	context.buildConstraintViolationWithTemplate("the words list "+AhoCorasickSearch.searchPatterns(value)).addConstraintViolation();
		return false;
    }
}