package day09;

public class Worker implements Runnable {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    // Entry point for a thread
    @Override
    public void run() {
        System.out.printf("I am %s\n", name);
    }
}