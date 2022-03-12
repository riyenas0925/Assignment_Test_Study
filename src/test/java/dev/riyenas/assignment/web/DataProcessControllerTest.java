package dev.riyenas.assignment.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataProcessController.class)
public class DataProcessControllerTest {

    @MockBean
    private DataProcessController dataProcessController;

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{index}. valid url {0}")
    @ValueSource(strings = {"http://google.com" , "https://google.com"})
    @DisplayName("url값은 http와 https를 포함해야 한다.")
    public void url(String validUrl) throws Exception {

        String json = "{\n" +
                "  \"url\" : \"" + validUrl + "\",\n" +
                "  \"exposureType\" : \"TEXT\",\n" +
                "  \"unit\" : 10\n" +
                "}\n";

        mockMvc.perform(post("/api/v1/process")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @ParameterizedTest(name = "{index}. invalide url \"{0}\"")
    @ValueSource(strings = {""})
    @DisplayName("url값이 없다면 Exeption이 발생한다.")
    public void urlNullException(String invalidUrl) throws Exception {

        String json = "{\n" +
                "  \"url\" : \"" + invalidUrl + "\",\n" +
                "  \"exposureType\" : \"TEXT\",\n" +
                "  \"unit\" : 10\n" +
                "}\n";

        mockMvc.perform(post("/api/v1/process")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @ParameterizedTest(name = "{index}. valid type {0}")
    @ValueSource(strings = {"HTML", "TEXT"})
    @DisplayName("노출 유형은 TEXT와 HTML만 허용한다.")
    public void exposureType(String validExposureTypes) throws Exception {

        String json = "{\n" +
                "  \"exposureType\" : \"" + validExposureTypes + "\",\n" +
                "  \"url\" : \"https://google.com\",\n" +
                "  \"unit\" : 10\n" +
                "}\n";

        mockMvc.perform(post("/api/v1/process")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(strings = {"JSON", "html", "text", ""})
    @DisplayName("노출 유형은 TEXT와 HTML외에 다른 유형이 들어오면 Exception을 발생시킨다.")
    public void exposureTypeException(String invalidExposureTypes) throws Exception {

        String json = "{\n" +
              "  \"exposureType\" : \"" + invalidExposureTypes + "\",\n" +
              "  \"url\" : \"https://google.com\",\n" +
              "  \"unit\" : 10\n" +
              "}\n";

        mockMvc.perform(post("/api/v1/process")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("묶음 단위가 1보다 작으면 Exception이 발생한다.")
    public void unitException() throws Exception {

        String json = "{\n" +
                "  \"exposureType\" : \"" + 0 + "\",\n" +
                "  \"url\" : \"https://google.com\",\n" +
                "  \"unit\" : 10\n" +
                "}\n";

        mockMvc.perform(post("/api/v1/process")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}
