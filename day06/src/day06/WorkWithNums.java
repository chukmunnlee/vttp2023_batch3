package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WorkWithNums {

    public static void main(String[] args) throws IOException {

        List<Integer> numList = new LinkedList<>();

        // Read the numbers into numList
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        int sum = 0;
        String line;

        while (null != (line = br.readLine())) {
            // String -> int
            int i = Integer.parseInt(line);
            // value < 0
            if (i < 0) {
                // Collecting the values into the list
                numList.add(i);
                //sum += i;
                sum = sum + i;
            }
        }

        System.out.printf("numList: %s (%d)\n", numList, numList.size());
        System.out.printf("sum: %d\n", sum);
    }
    
}
