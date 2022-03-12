package dev.riyenas.assignment.crawler;


import org.jsoup.nodes.Document;

public enum ExposureType {

    TEXT {
        @Override
        public String parse(Document document) {
            return document.html();
        }
    },
    HTML {
        @Override
        public String parse(Document document) {
            return document.text();
        }
    };

    public abstract String parse(Document document);

}
