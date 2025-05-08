package org;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleRunner {

    private final Scanner scanner;
    private final PrintStream outputPrintStream;
    private final PrintStream outputErrPrintStream;
    private final NumberToText numberToText;

    public ConsoleRunner(InputStream inputStream, PrintStream outputStream, PrintStream ErrPrintStream) {
        scanner = new Scanner(inputStream);
        outputPrintStream = outputStream;
        outputErrPrintStream = ErrPrintStream;
        numberToText = new NumberToText();
    }

    public void run(int maxAttempts) {
        int cAttempt = 0;
        do {
            try {
                outputPrintStream.print("Input a number: ");

                String inputLine = scanner.next();
                long value = Long.parseLong(inputLine);

                outputPrintStream.println("Your number: " + numberToText.numberToText(value));
                cAttempt = 0;
            } catch (Exception ex) {
                outputErrPrintStream.println("Error: " + ex.getMessage());
                cAttempt++;
            }
        } while (scanner.hasNext() && cAttempt < maxAttempts);

        if (cAttempt > 0) {
            outputErrPrintStream.println("Too much errors...");
        }
    }

    public void runTo(int maxAttempts) {
        int cAttempt = 0;
        outputPrintStream.print("Input a number: ");

        do {
            try {
                String inputLine = scanner.next();
                long value = Long.parseLong(inputLine);

                outputPrintStream.println("Your number: " + numberToText.numberToText(value));
                cAttempt = 0;
            } catch (Exception ex) {
                outputErrPrintStream.println("Error: " + ex.getMessage());
                cAttempt++;
            }
            outputPrintStream.print("Input a number: ");
        } while (scanner.hasNext() && cAttempt < maxAttempts);

        if (cAttempt > 0) {
            outputErrPrintStream.println("Too much errors...");
        }
    }
}
