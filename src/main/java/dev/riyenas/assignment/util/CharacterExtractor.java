package dev.riyenas.assignment.util;

import java.util.ArrayList;
import java.util.List;

public class CharacterExtractor {

    private final String data;

    public CharacterExtractor(String data) {
        this.data = data;
    }

    public List<Character> getAlphabets() {
        List<Character> alphabets = new ArrayList<>();

        for(char ch : data.toCharArray()) {
            if(isAlphabet(ch)) alphabets.add(ch);
        }

        return alphabets;
    }

    public List<Character> getNumbers() {
        List<Character> numbers = new ArrayList<>();

        for(char ch : data.toCharArray()) {
            if(isNumber(ch)) numbers.add(ch);
        }

        return numbers;
    }

    private boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private boolean isAlphabet(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

}
