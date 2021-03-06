package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

public class PageConnection {
    public static Document getPageByUrl(String url) {

        Document document = null;
        try {
            if (url.contains("file")) {
                BufferedReader fileReader = new BufferedReader(new FileReader(new File(new URL(url).toURI())));
                String page = fileReader.lines().collect(Collectors.joining());
                document = Jsoup.parse(page);
            } else {
                document = Jsoup.connect(url).get();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Invalid URL");
        }
        return document;
    }

}
