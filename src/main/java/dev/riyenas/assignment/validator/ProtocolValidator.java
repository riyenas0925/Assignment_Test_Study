package dev.riyenas.assignment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProtocolValidator implements ConstraintValidator<Protocol, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.contains("https") || value.contains("http")) {
            return true;
        }

        return false;
    }
}