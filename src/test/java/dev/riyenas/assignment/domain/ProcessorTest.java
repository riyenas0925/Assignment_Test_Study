package dev.riyenas.assignment.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest
public class ProcessorTest {

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("노출 유형이 HTML일 경우 HTML코드에서 태그를 제거한다.")
    void HTMLTypeRemoveTag() throws IOException {
        // given
        Resource resource = resourceLoader.getResource("crawler_test_web_page_input.html");
        Document document = Jsoup.parse(resource.getFile(), "UTF-8", "", Parser.xmlParser());

        Processor processor = new HTMLTypeProcessor(document);

        // when
        String expected = processor.getData(document);

        // that
        String actual = "Page Title My First Heading My Password 243590-812345-823714-902341-21340";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("노출 유형이 TEXT일 경우 HTML코드에서 태그를 제거한다.")
    void TextTypeRemoveTag() throws IOException {
        // given
        Resource resource = resourceLoader.getResource("crawler_test_web_page_input.html");
        Document document = Jsoup.parse(resource.getFile(), "UTF-8", "", Parser.xmlParser());

        Processor processor = new TextTypeProcessor(document);

        // when
        String expected = processor.getData(document);

        // that
        String actual = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "<p>My Password 243590-812345-823714-902341-21340</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @AfterEach
    void tearDown() {

    }
}
