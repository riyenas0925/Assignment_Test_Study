package dev.riyenas.assignment.domain;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private final String data;
    private final ExposureType type;

    public Processor(String data, ExposureType type) {
        this.data = data;
        this.type = type;
    }

    public String process() {
        return "A0a1B2b3";
    }

    public List<Character> extractAlpha() {
        List<Character> alphabets = new ArrayList<>();

        for(char ch : data.toCharArray()) {
            if(isAlphabet(ch)) alphabets.add(ch);
        }

        return alphabets;
    }

    private boolean isAlphabet(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    public List<Character> extractNumbers() {
        return new ArrayList<>();
    }

    public List<Character> shuffle(List<Character> numbers, List<Character> alphabets) {
        return new ArrayList<>();
    }

}
