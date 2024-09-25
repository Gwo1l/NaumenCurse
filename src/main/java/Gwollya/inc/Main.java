package Gwollya.inc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Task 1:\n");
        ArrayTask.start();

        System.out.println("\n ========================================");

        System.out.println("Task 2:\n");
        ListTask.start();

        System.out.println("\n ========================================");

        System.out.println("Task 3:\n");
        Employee.start();

        System.out.println("\n ========================================");

        System.out.println("Task 4:\n");
        HttpClientAndJSON.start();

        System.out.println("\n ========================================");

        System.out.println("Task 5:\n");
        Timer.startTimer();
    }
}
