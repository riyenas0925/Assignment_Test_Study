package dev.riyenas.assignment.service;

import dev.riyenas.assignment.crawler.Crawler;
import dev.riyenas.assignment.crawler.ExposureType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CrawlerService {

    public String crawl(String url, ExposureType type) throws IOException {
        Crawler crawler = new Crawler(type);
        return crawler.crawl(url);
    }

}
