package dev.riyenas.assignment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExposureValidator implements ConstraintValidator<Exposure, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.equals("HTML") || value.equals("TEXT")) {
            return true;
        }

        return false;
    }
}