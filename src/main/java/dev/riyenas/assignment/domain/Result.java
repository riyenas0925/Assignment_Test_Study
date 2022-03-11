package dev.riyenas.assignment.domain;

import lombok.Getter;

@Getter
public class Result {

    private final String quotient;
    private final String reminder;

    public Result(String quotient, String reminder) {
        this.quotient = quotient;
        this.reminder = reminder;
    }

}
