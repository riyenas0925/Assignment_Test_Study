package dev.riyenas.assignment.util;

import dev.riyenas.assignment.crawler.Crawler;
import dev.riyenas.assignment.crawler.ExposureType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@RestClientTest
public class CrawlerTest {

    private static final int PORT = 9000;
    private ClientAndServer mockServer;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    public void setUp() throws IOException {
        Resource resource = resourceLoader.getResource("test_web_page.html");
        byte[] content = resource.getInputStream().readAllBytes();

        mockServer = ClientAndServer.startClientAndServer(PORT);
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/web")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "text/html;charset=utf-8"))
                                .withBody(content)
                );
    }

    @Test
    @DisplayName("노출 유형이 HTML일 경우는 HTML 코드에서 태그를 제거한다.")
    public void crawlByVisibleTypeHTML() throws IOException {
        // given
        String url = "http://localhost:9000/web";

        // when
        String expected = new Crawler(ExposureType.HTML).crawl(url);

        // that
        String actual = "!@#$%^&*()_+ fedcba FEDCBA 654321";
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    @DisplayName("노출 유형이 TEXT일 경우에는 HTML 코드에서 모든 텍스트 포함한다.")
    public void crawlByVisibleTypeText() throws IOException {
        // given
        String url = "http://localhost:9000/web";

        // when
        String expected = new Crawler(ExposureType.TEXT).crawl(url);

        // that
        String actual = "<!doctype html>\n" +
                        "<html> \n" +
                        " <head> \n" +
                        "  <title>!@#$%^&amp;*()_+</title> \n" +
                        " </head> \n" +
                        " <body> \n" +
                        "  <p>fedcba</p> \n" +
                        "  <p>FEDCBA</p> \n" +
                        "  <p>654321</p>  \n" +
                        " </body>\n" +
                        "</html>";
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @AfterEach
    public void tearDown() {
        mockServer.stop();
    }

}