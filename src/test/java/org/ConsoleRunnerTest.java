package org;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class ConsoleRunnerTest {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void correctInputsShouldWork() {
        String userInputs = """
            100
            100
            100
            """;

        String expectedOutput = """
            Input a number: Your number: сто
            Input a number: Your number: сто
            Input a number: Your number: сто
            """;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        ConsoleRunner consoleRunner = new ConsoleRunner(
            new ByteArrayInputStream(userInputs.getBytes()),
            printStream,
            printStream
        );

        consoleRunner.run(3);

        Assertions.assertLinesMatch(expectedOutput.lines(), output.toString().lines());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void incorrectInputsShouldWork() {
        String userInputs = """
            100
            Abc
            bca
            100
            """;

        String expectedOutput = """
            Input a number: Your number: сто
            Input a number: Error: For input string: "Abc"
            Input a number: Error: For input string: "bca"
            Input a number: Your number: сто
            """;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        ConsoleRunner consoleRunner = new ConsoleRunner(
            new ByteArrayInputStream(userInputs.getBytes()),
            printStream,
            printStream
        );

        consoleRunner.run(3);

        Assertions.assertLinesMatch(expectedOutput.lines(), output.toString().lines());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void tooManyIncorrectInputsShouldAbortExecution() {
        String userInputs = """
            100
            Abc
            bca
            14hhh123
            9ddd
            100
            200
            """;

        String expectedOutput = """
            Input a number: Your number: сто
            Input a number: Input a number: Input a number:\s
            """;

        String expectedErrOutput = """
            Error: For input string: "Abc"
            Error: For input string: "bca"
            Error: For input string: "14hhh123"
            Too much errors...
            """;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayOutputStream errOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        PrintStream erPprintStream = new PrintStream(errOutput);

        ConsoleRunner consoleRunner = new ConsoleRunner(
            new ByteArrayInputStream(userInputs.getBytes()),
            printStream,
            erPprintStream
        );

        consoleRunner.run(3);

        Assertions.assertLinesMatch(expectedOutput.lines(), output.toString().lines());
        Assertions.assertLinesMatch(expectedErrOutput.lines(), errOutput.toString().lines());
    }
}
