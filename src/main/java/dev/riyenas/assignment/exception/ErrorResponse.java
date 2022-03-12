package dev.riyenas.assignment.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public static ErrorResponse of(List<FieldError> errors) {
        List<String> errorList = errors.stream()
                .map(t -> t.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorResponse(errorList);
    };

}
