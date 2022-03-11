package dev.riyenas.assignment.domain;

import org.jsoup.nodes.Document;

public class TextTypeProcessor extends Processor {
    public TextTypeProcessor(Document document) {
        super(document);
    }

    @Override
    public String getData(Document document) {
        return document.html();
    }
}
