package ru.mrchuvyzgalov;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlParser {
    public static List<String> parseHtmlFile(String path) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            StringBuilder htmlText = new StringBuilder();
            bufferedReader.lines().forEach(line -> htmlText.append(line + System.lineSeparator()));

            Document document = Jsoup.parse(htmlText.toString());

            return Arrays.stream(document.body().text().split("[.?!;\"\\[\\])(,:\\s+\\n\\r\\t]"))
                    .filter(word -> !word.isBlank())
                    .collect(Collectors.toList());
        }
    }
}
