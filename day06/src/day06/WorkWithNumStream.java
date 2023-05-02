package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class WorkWithNumStream {

    public static void main(String[] args) throws IOException {
        
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        List<String> numList = new LinkedList<>();
        String line;
        while (null != (line = br.readLine()))
            numList.add(line);

        fr.close();

        /*
         * for (String v: mumList) {
         *      int i = Integer.parseInt(v);
         *      if (i < 0)
         *         System.out.printf("i = %d\n", i);
         * } 
        */

        numList.stream()
            .map((String v ) -> Integer.parseInt(v))
            .filter((Integer v) -> (v < 0))
            .forEach((Integer v) -> {
                System.out.printf("v = %d\n", v);
            });

        /*
         * for (String v: mumList) {
         *      int i = Integer.parseInt(v);
         *      if (i < 0)
         *         intList.add(i);
         * } 
        */
        List<Integer> intList = numList.stream()
            .map((String v ) -> Integer.parseInt(v))
            .filter((Integer v) -> (v < 0))
            .toList();

        System.out.println(intList);

        /*
         * int sum = 0;
         * for (String v: mumList) {
         *      int i = Integer.parseInt(v);
         *      if (i < 0)
         *         sum = sum + i;
         * } 
        */
        int total = numList.stream()
            .map((String v ) -> Integer.parseInt(v))
            .filter((Integer v) -> (v < 0))
            .reduce(0, (Integer sum, Integer i) -> sum + i);

        System.out.printf("Total: %d\n", total);

    }
    
}
