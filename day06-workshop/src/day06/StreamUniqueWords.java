package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StreamUniqueWords {

    public static void main(String[] args) throws IOException {

        Reader r = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(r);

        Map<String, Integer> wordCount = new HashMap<>();
        
        br.lines()
            .map(line -> line.trim().toLowerCase().replaceAll("\\p{Punct}", ""))
            // String -> String[]
            .map(line -> line.split("\\s+"))
            .flatMap(words -> Arrays.asList(words).stream())
            .filter(word -> (word.trim().length() > 0))
            .filter(word -> !Constants.STOPWORDS_SET.contains(word))
            .forEach(word -> {
                int c = wordCount.computeIfAbsent(word, w -> 0);
                wordCount.put(word, c + 1);
            });

        br.close();

        for (String k: wordCount.keySet()) 
            System.out.printf("word: %s, count: %d\n", k, wordCount.get(k));

        long single = wordCount.keySet().stream()
            .filter((String word) -> wordCount.get(word) == 1)
            .count();

        System.out.printf(">>> single words: %d\n", single);
    }
}