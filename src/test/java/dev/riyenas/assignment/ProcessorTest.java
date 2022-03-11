package dev.riyenas.assignment;

import dev.riyenas.assignment.domain.ExposureType;
import dev.riyenas.assignment.domain.Processor;
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
        List<Character> expected = processor.extractAlpha();

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

}
