package day09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable {

    @Override
    public void run() {
        System.out.printf("I am from the Main class");
    }

    // Main program's entry point
    public static void main(String[] args) {
        String[] names = { "fred", "barney", "wilma", "betty" };
        // Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        System.out.println("---- I am the main thread");
        for (String n: names) {
            System.out.printf("---- Scheduling %s\n", n);
            Worker w = new Worker(n);
            //w.run();
            thrPool.submit(w);
        }

        // Create an instance of Main and submit to thread pool
        thrPool.submit(new Main());

        System.out.printf("---- All done\n");
        thrPool.shutdown();
    }

}