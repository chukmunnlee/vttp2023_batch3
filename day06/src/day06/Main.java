package day06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Greetings g = new Greetings();

        Runnable a = g;
        Function<String, String> f = g;
        a.run();
        System.out.printf("f.apply %s\n", f.apply("hello, world"));

        // Lambda expression 
        Runnable r = () -> {
            System.out.println("**** hello, world *****");
        };
        Function<String, String> s = (String x) -> {
            return x.toLowerCase();
        };

        r.run();
        System.out.printf("****** apply %s\n", s.apply("hello, world"));
    }

}