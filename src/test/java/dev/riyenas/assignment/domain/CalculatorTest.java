package dev.riyenas.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @ParameterizedTest(name = "{index}. data: {0}, unit: \"{1}\" -> \"{2}\" + \"{3}\"")
    @CsvSource(value = {
            "A0a1B2b3:1:A0a1B2b3:''",
            "A0a1B2b3:3:A0a1B2:b3",
            "A0a1B2b3:7:A0a1B2b:'3'",
            "A0a1B2b3:8:A0a1B2b3:''",
            "A0a1B2b3:9:'':A0a1B2b3",
    }, delimiter = ':')
    @DisplayName("몫과 나머지를 계산한다.")
    public void processDataQuotientAndReminder(String data, int unit, String quotient, String reminder) {
        // given
        Calculator calculator = new Calculator(data, unit);

        // when
        Result expected = calculator.calculate();

        // that
        assertThat(expected.getQuotient()).isEqualTo(quotient);
        assertThat(expected.getReminder()).isEqualTo(reminder);
    }

}
