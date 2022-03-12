package dev.riyenas.assignment.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {

    private final static String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Mobile Safari/537.36";

    private final ExposureType exposureType;

    public Crawler(ExposureType exposureType) {
        this.exposureType = exposureType;
    }

    public String crawl(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .get();

        return exposureType.parse(document);
    }

}
