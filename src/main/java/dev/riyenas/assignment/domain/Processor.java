package dev.riyenas.assignment.domain;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private final List<Character> numbers;
    private final List<Character> alphabets;

    public Processor(List<Character> alphabets, List<Character> numbers) {
        this.alphabets = alphabets;
        this.numbers = numbers;
    }

    public List<Character> shuffle() {
        List<Character> data = new ArrayList<>();

        int alphabetsSize = alphabets.size();
        int numbersSize = numbers.size();
        int maxSize = Math.max(numbersSize, alphabetsSize);

        for(int i = 0; i < maxSize; i++) {
            if(i < alphabetsSize) data.add(alphabets.get(i));
            if(i < numbersSize) data.add(numbers.get(i));
        }

        return data;
    }

}
