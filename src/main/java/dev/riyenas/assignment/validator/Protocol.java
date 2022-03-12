package dev.riyenas.assignment.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProtocolValidator.class)
public @interface Protocol {
    String message() default "";
    Class[] groups() default {};
    Class[] payload() default {};
}