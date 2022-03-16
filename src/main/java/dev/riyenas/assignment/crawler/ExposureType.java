package dev.riyenas.assignment.crawler;

import org.jsoup.nodes.Document;

import java.util.function.Function;

public enum ExposureType {

    TEXT(document -> document.html()),
    HTML(document -> document.text());

    private final Function<Document, String> function;

    ExposureType(Function<Document, String> function) {
        this.function = function;
    }

    public String parse(Document document) {
        return function.apply(document);
    }
}
