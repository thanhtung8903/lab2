package com.lab.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    public void testInputChoiceInvalidInput() throws Exception {
        // Mocking user input
        String input = "invalid\n"; // First input is invalid
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in);

        // Capture System.out
        String output = tapSystemOut(() -> {
            try {
                Validation.inputChoice("Enter your choice: ", 1, 5);
            } catch (Exception e) {
                // Catch the exception to prevent the test from failing
            }
        });

        // Verify the output contains the expected error message
        assertTrue(output.contains("Invalid, please input again!"));
    }

    @Test
    void testValidInput() {
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream


        int result = Validation.inputChoice("Enter a number: ", 1, 10);
        assertEquals(5, result);
    }

    @Test
    void testInvalidInputThenValidInput() {
        String input = "invalid\n7\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream

        int result = Validation.inputChoice("Enter a number: ", 1, 10);
        assertEquals(7, result);


    }

    @Test
    void testInputOutOfRangeThenValidInput() {
        String input = "15\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream

        int result = Validation.inputChoice("Enter a number: ", 1, 10);
        assertEquals(3, result);

    }

    //        exception when input is not a number
    @Test
    void testInputNotNumber() {
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(NoSuchElementException.class, () -> {
            Validation.inputChoice("Enter a number: ", 1, 10);
        });
    }


    @ParameterizedTest
    @CsvSource({
            "'nguyen thanh tung\n', 'Nguyen Thanh Tung'",
            "'john DOE\n', 'John Doe'",
            "'alice    smith\n', 'Alice Smith'",
            "'MARy jAnE\n', 'Mary Jane'",
            "'ADam     lEviNE\n', 'Adam Levine'"
    })
    public void testInputNameNormalize(String input, String expected) {
        InputStream originalIn = System.in;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in);

        String result = Validation.inputNameNormalize("Enter your name: ", "^[A-Za-z\\s]+$");
        assertEquals(expected, result);
    }

    //    inputCourseName
    @ParameterizedTest
    @CsvSource({
            "'Java\n', 'Java'",
            "'.Net\n', '.Net'",
            "'C/C++\n', 'C/C++'",
            "'jAVa\n', 'Java'",
            "'.net\n', '.Net'",
            "'c/c++\n', 'C/C++'"
    })
    void testInputCourseNameValid(String input, String expected) {
        InputStream originalIn = System.in; // Save the original System.in

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream

        String result = Validation.inputCourseName("Enter course name: ");
        assertEquals(expected, result);

    }

    @ParameterizedTest
    @CsvSource({
            "'Javascript\n', 'Java'",
            "'.Nefft\n', '.Net'",
            "'C/C+gsdf+\n', 'C/C++'",
            "'jAVgdsfa\n', 'Java'",
            "'.ngdfset\n', '.Net'",
            "'c/dsfgc++\n', 'C/C++'"
    })
    void testInputCourseNameInvalid(String input, String expected) {
        InputStream originalIn = System.in; // Save the original System.in

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream

        assertThrows(NoSuchElementException.class, () -> {
            Validation.inputCourseName("Enter course name: ");
        });

    }

}