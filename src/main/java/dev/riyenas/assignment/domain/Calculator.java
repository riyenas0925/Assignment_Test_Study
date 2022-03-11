package dev.riyenas.assignment.domain;

public class Calculator {

    private final String data;
    private final int unit;

    public Calculator(String data, int unit) {
        this.data = data;
        this.unit = unit;
    }

    public Result calculate() {
        return new Result(quotient(), reminder());
    }

    private String quotient() {
        int length = data.length();
        int quotient = length / unit;
        int lastIndex = Math.min(length, quotient * unit);

        return data.substring(0, lastIndex);
    }

    private String reminder() {
        int length = data.length();
        int quotient = length / unit;
        int startIndex = Math.min(quotient * unit, length);

        return data.substring(startIndex, length);
    }

}