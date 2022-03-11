package dev.riyenas.assignment.domain;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private final char[] data;
    private final ExposureType type;

    public Processor(String data, ExposureType type) {
        this.data = data.toCharArray();
        this.type = type;
    }

    public String process() {
        return "A0a1B2b3";
    }

    public List<Character> extractAlphabets() {
        List<Character> alphabets = new ArrayList<>();

        for(char ch : data) {
            if(isAlphabet(ch)) alphabets.add(ch);
        }

        return alphabets;
    }

    private boolean isAlphabet(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    public List<Character> extractNumbers() {
        List<Character> numbers = new ArrayList<>();

        for(char ch : data) {
            if(isNumber(ch)) numbers.add(ch);
        }

        return numbers;
    }

    private boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public List<Character> shuffle(List<Character> numbers, List<Character> alphabets) {
        return new ArrayList<>();
    }

}
