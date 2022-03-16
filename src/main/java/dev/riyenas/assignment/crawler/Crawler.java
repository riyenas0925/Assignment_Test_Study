package dev.riyenas.assignment.crawler;

import dev.riyenas.assignment.error.exception.CrawlerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {

    private final static String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Mobile Safari/537.36";

    private final ExposureType exposureType;

    public Crawler(ExposureType exposureType) {
        this.exposureType = exposureType;
    }

    public String crawl(String url) throws CrawlerException {

        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .get();
        } catch (IOException e) {
            throw new CrawlerException();
        }

        return exposureType.parse(document);
    }

}
