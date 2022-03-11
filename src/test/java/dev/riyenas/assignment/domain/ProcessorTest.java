package dev.riyenas.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessorTest {

    @Test
    @DisplayName("HTML 코드에서 영어를 추출한다.")
    public void extractAlphabets() {
        // given
        String exposureData = "!@#$%^&*()_+ az AZ 09";
        Processor processor = new Processor(exposureData, ExposureType.TEXT);

        // when
        List<Character> expected = processor.extractAlphabets();

        // that
        List<Character> actual = List.of('a', 'z', 'A', 'Z');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("HTML 코드에서 숫자를 추출한다.")
    public void extractNumbers() {
        // given
        String exposureData = "!@#$%^&*()_+ az AZ 09";
        Processor processor = new Processor(exposureData, ExposureType.TEXT);

        // when
        List<Character> expected = processor.extractNumbers();

        // that
        List<Character> actual = List.of('0', '9');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("영어가 숫자보다 많을 때 교차출력 후 나머지 영어를 출력한다.")
    public void shuffleAlphabetsAndNumbersCase1() {
        // given
        Processor processor = new Processor("", ExposureType.TEXT);
        List<Character> characters = List.of('A', 'B', 'c', 'd');
        List<Character> numbers = List.of('1', '2');

        // when
        List<Character> expected = processor.shuffle(characters, numbers);

        // that
        List<Character> actual = List.of('A', '1', 'B', '2', 'c', 'd');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자가 영어보다 많을 때 교차출력 후 나머지 숫자를 출력한다.")
    public void shuffleAlphabetsAndNumbersCase2() {
        // given
        Processor processor = new Processor("", ExposureType.TEXT);
        List<Character> characters = List.of('A', 'b');
        List<Character> numbers = List.of('1', '2', '3', '4');

        // when
        List<Character> expected = processor.shuffle(characters, numbers);

        // that
        List<Character> actual = List.of('A', '1', 'b', '2', '3', '4');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자와 영어의 개수가 같을떄 교차 출력한다.")
    public void shuffleAlphabetsAndNumbersCase3() {
        // given
        Processor processor = new Processor("", ExposureType.TEXT);
        List<Character> characters = List.of('A', 'B');
        List<Character> numbers = List.of('1', '2');

        // when
        List<Character> expected = processor.shuffle(characters, numbers);

        // that
        List<Character> actual = List.of('A', '1', 'B', '2');
        assertThat(actual).isEqualTo(expected);
    }

}
