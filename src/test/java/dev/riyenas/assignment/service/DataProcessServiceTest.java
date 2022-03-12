package dev.riyenas.assignment.service;

import dev.riyenas.assignment.web.dto.ProcessResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(value={DataProcessService.class})
public class DataProcessServiceTest{

    @Autowired
    private DataProcessService dataProcessService;

    @ParameterizedTest
    @CsvSource(value = {
            "!@#$%^&*()_+ fedcba FEDCBA 654321:5:A1a2B3b4C5c6DdE:eFf",
            "A0a1B2b3C4c5:5:A0a1B2b3C4:c5",
            "A0a1B2b3C4c5:12:A0a1B2b3C4c5:''",
            "A0a1B2b3C4c5:13:'':A0a1B2b3C4c5"
    }, delimiter = ':')
    @DisplayName("크롤링된 데이터를 영어와 숫자를 가공해 몫과 나머지를 구한다.")
    public void dataProcess(String data, int unit, String quotient, String reminder) {
        // given

        // when
        ProcessResponseDto expected = dataProcessService.dataProcess(data, unit);

        // that
        assertThat(quotient).isEqualTo(expected.getQuotient());
        assertThat(reminder).isEqualTo(expected.getReminder());
    }

}
