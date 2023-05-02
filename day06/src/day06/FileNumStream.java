package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class FileNumStream {

    public static void main(String[] args) throws Exception {

        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);

        int total = br.lines()
            .map((String v) -> Integer.parseInt(v))
            .filter((Integer i) -> (i < 0))
            .reduce(0, (Integer sum, Integer i) -> sum + i);

        System.out.printf("total: %d\n", total);

        fr = new FileReader(args[0]);
        br = new BufferedReader(fr);

        long negCount = br.lines()
            .map((String v) -> Integer.parseInt(v))
            .filter((Integer i) -> (i < 0))
            .count();

        System.out.printf("neg num: %d\n", negCount);

        fr = new FileReader(args[0]);
        br = new BufferedReader(fr);

        List<Integer> sortedList = br.lines()
            .map((String v) -> Integer.parseInt(v))
            .filter((Integer i) -> (i < 0))
            .distinct()
            .sorted()
            .toList();

        System.out.printf(">> sorted: %s\n", sortedList);

    }
    
}
