package dev.riyenas.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterExtractorTest {

    @Test
    @DisplayName("HTML 코드에서 영어를 추출한다.")
    public void extractAlphabets() {
        // given
        String exposureData = "!@#$%^&*()_+ az AZ 09";
        CharacterExtractor characterExtractor = new CharacterExtractor(exposureData);

        // when
        List<Character> expected = characterExtractor.alphabets();

        // that
        List<Character> actual = List.of('a', 'z', 'A', 'Z');
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("HTML 코드에서 숫자를 추출한다.")
    public void extractNumbers() {
        // given
        String exposureData = "!@#$%^&*()_+ az AZ 09";
        CharacterExtractor characterExtractor = new CharacterExtractor(exposureData);

        // when
        List<Character> expected = characterExtractor.numbers();

        // that
        List<Character> actual = List.of('0', '9');
        assertThat(actual).isEqualTo(expected);
    }

}
