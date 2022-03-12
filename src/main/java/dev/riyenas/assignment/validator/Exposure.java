package dev.riyenas.assignment.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExposureValidator.class)
public @interface Exposure {
    String message() default "잘못된 노출 유형입니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}
