package dev.riyenas.assignment.domain;

import java.util.ArrayList;
import java.util.List;

public class Extractor {

    private final String data;

    public Extractor(String data) {
        this.data = data;
    }

    public List<Character> alphabets() {
        List<Character> alphabets = new ArrayList<>();

        for(char ch : data.toCharArray()) {
            if(isAlphabet(ch)) alphabets.add(ch);
        }

        return alphabets;
    }

    private boolean isAlphabet(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    public List<Character> numbers() {
        List<Character> numbers = new ArrayList<>();

        for(char ch : data.toCharArray()) {
            if(isNumber(ch)) numbers.add(ch);
        }

        return numbers;
    }

    private boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }
}
