package dev.riyenas.assignment.crawler;

import dev.riyenas.assignment.domain.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
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
    void setUp() throws IOException {
        byte[] content = resourceLoader.getResource("crawler_test_web_page_input.html")
                .getInputStream().readAllBytes();

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
    @DisplayName("URL을 입력하면 해당 URL의 모든 HTML 코드를 불러온다.")
    void getHtmlCode() throws IOException {
        // given
        Crawler crawler = new Crawler();

        Resource resource = resourceLoader.getResource("crawler_test_web_page_input.html");
        Document input = Jsoup.parse(resource.getFile(), "UTF-8", "", Parser.xmlParser());

        // when
        Document expected = crawler.getHtmlCode("http://localhost:9000/web");

        // that
        assertThat(input.html()).isEqualTo(expected.html());
    }

    @AfterEach
    public void tearDown() {
        mockServer.stop();
    }

}