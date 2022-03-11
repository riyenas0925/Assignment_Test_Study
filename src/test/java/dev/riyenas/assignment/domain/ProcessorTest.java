package dev.riyenas.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
