package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UniqueWords {

    public static void main(String[] args) throws IOException {

        Reader r = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(r);
        String line;

        Map<String, Integer> wordCount = new HashMap<>();

        while (null != (line = br.readLine())) {
            line = line.trim().toLowerCase().replaceAll("\\p{Punct}", "");
            for (String w: line.split("\\s+")) {
                String tmp = w.trim();
                if ((tmp.length() <= 0) || (Constants.STOPWORDS_SET.contains(tmp)))
                    continue;
                /*
                if (!wordCount.containsKey(tmp))
                    wordCount.put(tmp, 0);
                wordCount.put(tmp, wordCount.get(tmp) + 1);
                */
                int count = wordCount.computeIfAbsent(tmp, (String k) -> 0);
                wordCount.put(tmp, count + 1);
            }
        }

        br.close();

        for (String k: wordCount.keySet()) {
            System.out.printf("word: %s, count: %d\n", k, wordCount.get(k));
        }

        long single = wordCount.keySet().stream()
            .filter((String word) -> wordCount.get(word) == 1)
            .count();

        System.out.printf(">>> single words: %d\n", single);

    }
}