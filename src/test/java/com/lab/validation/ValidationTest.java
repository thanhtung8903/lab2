package com.lab.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {


    @ParameterizedTest
    @ValueSource(strings = {"5", "1", "10"})
    @DisplayName("Test inputChoice with valid choices")
    public void testInputChoice_ValidChoice(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        int min = 1;
        int max = 10;
        assertEquals(Integer.parseInt(input), Validation.inputChoice("Enter choice: ", min, max));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "11"})
    @DisplayName("Test inputChoice with choices outside the valid range")
    public void testInputChoice_InvalidChoice(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();

        int min = 1;
        int max = 10;
        assertThrows(NumberFormatException.class, () -> {
            Validation.inputChoice("Enter choice: ", min, max);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12.5", " "})
    @DisplayName("Test inputChoice with non-numeric input")
    public void testInputChoice_NonNumericInput(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();

        int min = 1;
        int max = 10;
        assertThrows(NumberFormatException.class, () -> {
            Validation.inputChoice("Enter choice: ", min, max);
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"HE173024", "HS183245", "SE170945"})
    @DisplayName("Test inputId with valid IDs")
    public void testInputId_ValidId(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputId("Enter ID: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1234567", "A1B2C3D4E5", "abc@1234", "   "})
    @DisplayName("Test inputId with invalid IDs")
    public void testInputId_InvalidId(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputId("Enter ID: ");
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"0", "5", "9"})
    @DisplayName("Test inputSemester with valid semesters")
    public void testInputSemester_ValidSemester(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputSemester("Enter semester: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "a", "1.5", "-1"})
    @DisplayName("Test inputSemester with invalid semesters")
    public void testInputSemester_InvalidSemester(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputSemester("Enter semester: ");
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"Y", "y"})
    @DisplayName("Test inputYesNO with 'Y' or 'y'")
    public void testInputYesNO_Yes(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputYesNO("Enter Y or N: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"N", "n"})
    @DisplayName("Test inputYesNO with 'N' or 'n'")
    public void testInputYesNO_No(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertFalse(Validation.inputYesNO("Enter Y or N: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "yes", "no", "123", " "})
    @DisplayName("Test inputYesNO with invalid inputs")
    public void testInputYesNO_Invalid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputYesNO("Enter Y or N: ");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"U", "u"})
    @DisplayName("Test inputUD with 'U' or 'u'")
    public void testInputUD_U(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputUD("Enter U or D: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"D", "d"})
    @DisplayName("Test inputUD with 'D' or 'd'")
    public void testInputUD_D(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertFalse(Validation.inputUD("Enter U or D: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "up", "down", "123", " "})
    @DisplayName("Test inputUD with invalid inputs")
    public void testInputUD_Invalid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputUD("Enter U or D: ");
        });
    }


    @ParameterizedTest
    @CsvSource({
            "hello, ^[a-zA-Z]+$",
            "World, ^[a-zA-Z]+$",
            "12345, ^\\d+$"
    })
    @DisplayName("Test inputString with valid inputs")
    public void testInputString_Valid(String input, String regex) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertEquals(input, Validation.inputString("Enter input: ", regex));
    }

    @ParameterizedTest
    @CsvSource({
            "hello123, ^[a-zA-Z]+$",
            "World!, ^[a-zA-Z]+$",
            "abc123, ^\\d+$"
    })
    @DisplayName("Test inputString with invalid inputs")
    public void testInputString_Invalid(String input, String regex) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputString("Enter input: ", regex);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0987654321", "0901234567"})
    @DisplayName("Test inputPhone with valid phone numbers")
    public void testInputPhone_Valid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputPhone("Enter phone number: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "00123456789", "abcdefghij", "01234", "01234567890"})
    @DisplayName("Test inputPhone with invalid phone numbers")
    public void testInputPhone_Invalid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputPhone("Enter phone number: ");
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user123@domain.co", "surname@company.org.fu"})
    @DisplayName("Test inputEmail with valid email addresses")
    public void testInputEmail_Valid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertTrue(Validation.inputEmail("Enter email address: "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"plainaddress", "@missingusername.com", "username@.com", "username@domain", "username@domain..com"})
    @DisplayName("Test inputEmail with invalid email addresses")
    public void testInputEmail_Invalid(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
        Validation.resetScanner();
        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputEmail("Enter email address: ");
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
//        InputStream originalIn = System.in; // Save the original System.in

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Validation.sc = new Scanner(System.in); // Reinitialize the Scanner with the new InputStream

        assertThrows(IllegalArgumentException.class, () -> {
            Validation.inputCourseName("Enter course name: ");
        });

    }

}