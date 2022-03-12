package dev.riyenas.assignment.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private List<String> errors;
    private String code;

    public ErrorResponse(List<String> errors, String code) {
        this.errors = errors;
        this.code = code;
    }

    public static ErrorResponse of(List<FieldError> errors, String code) {
        List<String> errorMessages = errors.stream()
                .map(t -> t.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorResponse(errorMessages, code);
    };

    public static ErrorResponse of(String message, String code) {
        return new ErrorResponse(Arrays.asList(message), code);
    }

}
