package dev.riyenas.assignment.domain;

import org.jsoup.nodes.Document;

public class HTMLTypeProcessor extends Processor{

    public HTMLTypeProcessor(Document document) {
        super(document);
    }

    @Override
    public String getData(Document document) {
        return document.text();
    }
}
