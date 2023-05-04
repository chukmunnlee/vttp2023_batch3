package day08.playstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        Reader r = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(r);

        Map<String, PlaystoreStats> stats = new HashMap<>();

        // Ignore the first line
        //br.readLine();

        br.lines()
            .skip(1) // ignore the first line 

            .filter(line -> !line.contains("NaN")) // lines that don't have NaN

            .map(line -> line.split(",")) // String -> String[]

            .map(cols -> new PlaystoreEntry(cols[0].trim(), cols[1].trim(), Float.parseFloat(cols[2].trim()) )) // String[] -> PlaystoreEntry

            .collect(Collectors.groupingBy(entry -> entry.category())) // PlaystoreEntry -> Map<String, List<PlaystoreEntry>>

            .forEach((String category, List<PlaystoreEntry> entries) -> {
                PlaystoreStats s = new PlaystoreStats(category);
                for(PlaystoreEntry e: entries)
                    s.compute(e);
                stats.put(category, s);
            });


        for (String c: stats.keySet()) {
            PlaystoreStats s = stats.get(c);
            System.out.printf("%s: avg: %f, ^%s:%f, V%s:%f\n", c, s.averageRating()
                    , s.getHighestRatedApp(), s.getHighestRating(), s.getLowestRatedApp(), s.getLowestRating());
        }

    }
    
}
