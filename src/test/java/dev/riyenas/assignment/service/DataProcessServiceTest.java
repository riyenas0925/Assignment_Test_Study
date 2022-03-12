package dev.riyenas.assignment.service;

import dev.riyenas.assignment.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(value={DataProcessService.class})
public class DataProcessServiceTest{

    @Autowired
    private DataProcessService dataProcessService;

    @Test
    @DisplayName("크롤링된 데이터를 영어와 숫자를 가공해 몫과 나머지를 구한다.")
    public void dataProcess() {
        // given
        String data = "!@#$%^&*()_+ fedcba FEDCBA 654321";

        // when
        Result expected = dataProcessService.dataProcess(data, 5);

        // that
        assertThat("A1a2B3b4C5c6DdE").isEqualTo(expected.getQuotient());
        assertThat("eFf").isEqualTo(expected.getReminder());
    }

}
