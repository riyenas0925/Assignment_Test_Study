package dev.riyenas.assignment.web.dto;

import dev.riyenas.assignment.util.Result;
import lombok.Getter;

@Getter
public class ProcessResponseDto {

    private final String quotient;
    private final String reminder;

    public ProcessResponseDto(Result result) {
        this.quotient = result.getQuotient();
        this.reminder = result.getReminder();
    }

}
