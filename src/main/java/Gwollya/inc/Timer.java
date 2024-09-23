package Gwollya.inc;

import java.util.Objects;
import java.util.Scanner;

public class Timer implements Task {
    private int seconds;
    private static boolean isStopped = false;

    public Timer(int seconds) {
        this.seconds = seconds;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer(scanner.nextInt());
        timer.start();
        String stopWord = scanner.next();
        if (!stopWord.isEmpty()) {
            timer.stop();
        }
    }

    @Override
    public void start() {
        new Thread(() -> {
            while (!isStopped && seconds > 0) {
                System.out.println(seconds);
                seconds--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        isStopped = true;
    }
}
