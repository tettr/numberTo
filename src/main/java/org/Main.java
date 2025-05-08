package org;

public class Main {
    public static void main(String[] args) {
        ConsoleRunner consoleRunner = new ConsoleRunner(System.in, System.out, System.out);

        int maxAttempts = 3;
        consoleRunner.runTo(maxAttempts);
    }
}