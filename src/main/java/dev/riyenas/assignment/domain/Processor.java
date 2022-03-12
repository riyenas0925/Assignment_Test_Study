package dev.riyenas.assignment.domain;

import lombok.Builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Processor {

    private final List<Character> numbers;
    private final List<Character> alphabets;

    private final static Comparator<Character> sort = (c1, c2) -> {
        if(Character.toUpperCase(c1) == Character.toUpperCase(c2)) {
            return c1 - c2;
        }

        return Character.toUpperCase(c1) - Character.toUpperCase(c2);
    };

    @Builder
    public Processor(List<Character> alphabets, List<Character> numbers) {
        this.alphabets = alphabets;
        this.numbers = numbers;
    }

    public String process() {
        Collections.sort(numbers);
        Collections.sort(alphabets, sort);

        return shuffle().stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
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
