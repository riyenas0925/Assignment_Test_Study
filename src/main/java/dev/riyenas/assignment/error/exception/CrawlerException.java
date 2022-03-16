package dev.riyenas.assignment.error.exception;

import java.io.IOException;

public class CrawlerException extends IOException {

    private static final String message = "크롤링 중 오류가 발생했습니다. URL과 사이트를 확인해주세요.";

    public CrawlerException() {
        super(message);
    }

}
