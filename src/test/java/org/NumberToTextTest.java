package org;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class NumberToTextTest {

    private final NumberToText converter = new NumberToText();

    @ParameterizedTest
    @CsvFileSource(resources = "/small-test-data.csv")
    void smallModelTest(long inputNumber, String expectedOutput) {
        Assertions.assertEquals(expectedOutput, converter.numberToText(inputNumber));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/large-test-data.csv")
    void largeModelTest(long inputNumber, String expectedOutput) {
        Assertions.assertEquals(expectedOutput, converter.numberToText(inputNumber));
    }
}
