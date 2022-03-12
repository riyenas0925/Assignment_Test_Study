package dev.riyenas.assignment.service;

import dev.riyenas.assignment.crawler.Crawler;
import dev.riyenas.assignment.crawler.ExposureType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrawlerService {

    public String crawler(String url, ExposureType type) throws IOException {
        return Crawler.crawl(url, type);
    }

}
