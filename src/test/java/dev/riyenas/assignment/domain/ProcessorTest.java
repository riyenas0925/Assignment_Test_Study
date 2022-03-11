package dev.riyenas.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessorTest {

    @Test
    @DisplayName("영어가 숫자보다 많을 때 교차출력 후 나머지 영어를 출력한다.")
    public void shuffleAlphabetsAndNumbersCase1() {
        // given
        List<Character> characters = List.of('A', 'B', 'c', 'd');
        List<Character> numbers = List.of('1', '2');
        Processor processor = new Processor(characters, numbers);

        // when
        List<Character> expected = processor.shuffle();

        // that
        List<Character> actual = List.of('A', '1', 'B', '2', 'c', 'd');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자가 영어보다 많을 때 교차출력 후 나머지 숫자를 출력한다.")
    public void shuffleAlphabetsAndNumbersCase2() {
        // given
        List<Character> characters = List.of('A', 'b');
        List<Character> numbers = List.of('1', '2', '3', '4');
        Processor processor = new Processor(characters, numbers);

        // when
        List<Character> expected = processor.shuffle();

        // that
        List<Character> actual = List.of('A', '1', 'b', '2', '3', '4');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자와 영어의 개수가 같을떄 교차 출력한다.")
    public void shuffleAlphabetsAndNumbersCase3() {
        // given
        List<Character> characters = List.of('A', 'B');
        List<Character> numbers = List.of('1', '2');
        Processor processor = new Processor(characters, numbers);

        // when
        List<Character> expected = processor.shuffle();

        // that
        List<Character> actual = List.of('A', '1', 'B', '2');
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{index}. actual: {0} <- alps: {1}, nums: {2}")
    @MethodSource("generateData")
    @DisplayName("영어와 숫자를 각각 정렬하고 교차 출력한다.")
    public void process(String actual, List<Character> inputAlphabets, List<Character> inputNumbers) {
        // given
        Processor processor = new Processor(inputAlphabets, inputNumbers);

        // when
        String expected = processor.process();

        // that
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('A', 'B', 'C', 'a', 'b', 'c'), Arrays.asList('0', '1', '2', '3', '4', '5')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('a', 'b', 'c', 'A', 'B', 'C'), Arrays.asList('0', '1', '2', '3', '4', '5')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('C', 'B', 'A', 'a', 'b', 'c'), Arrays.asList('5', '4', '3', '2', '1', '0')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('c', 'b', 'a', 'A', 'B', 'C'), Arrays.asList('5', '4', '3', '2', '1', '0')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('A', 'a', 'B', 'b', 'C', 'c'), Arrays.asList('0', '5', '1', '4', '2', '3')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('a', 'A', 'b', 'B', 'c', 'C'), Arrays.asList('0', '5', '1', '4', '2', '3')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('C', 'c', 'B', 'b', 'A', 'a'), Arrays.asList('0', '5', '1', '4', '2', '3')),
                Arguments.of("A0a1B2b3C4c5", Arrays.asList('c', 'C', 'b', 'B', 'a', 'A'), Arrays.asList('0', '5', '1', '4', '2', '3'))
        );
    }

}
