package dev.riyenas.assignment.domain;

import org.jsoup.nodes.Document;

public abstract class Processor {

    private Document document;

    public Processor(Document document) {
        this.document = document;
    }

    public String process() {
        return getData(document);
    }

    public abstract String getData(Document document);
}