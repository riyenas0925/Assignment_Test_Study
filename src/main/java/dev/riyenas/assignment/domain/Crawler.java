package dev.riyenas.assignment.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {

    private final static String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Mobile Safari/537.36";

    // block instantiation
    private Crawler() {

    }

    public static String crawl(String url, ExposureType exposureType) throws IOException {
        Document document = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .get();

        if(exposureType.equals(ExposureType.HTML)) {
            return document.text();
        }

        return document.html();
    }

}
