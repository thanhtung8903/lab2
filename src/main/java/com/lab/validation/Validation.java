package com.lab.validation;

import java.util.Scanner;

public class Validation {

    static Scanner sc = new Scanner(System.in);

    public static void resetScanner() {
        sc = new Scanner(System.in);
    }

    private static String getInput(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public static int inputChoice(String msg, int min, int max) {
        int choice = Integer.parseInt(getInput(msg));
        if (choice < min || choice > max) {
            throw new NumberFormatException("Invalid, please input again!");
        }
        return choice;
    }

    public static boolean inputId(String msg) {
        String input = getInput(msg);
        if (!input.matches("[A-Z]{2}\\d{6}")) {
            throw new IllegalArgumentException("Invalid, please input again!");
        } else {
            return true;
        }
    }

    public static boolean inputSemester(String msg) {
        String input = getInput(msg);
        if (!input.matches("^[0-9]$")) {
            throw new IllegalArgumentException("Invalid, please input again!");
        } else {
            return true;
        }
    }

    public static boolean inputYesNO(String msg) {
        String input = getInput(msg).trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else if (input.equalsIgnoreCase("N")) {
            return false;
        } else {
            throw new IllegalArgumentException("You must input Y or N.");
        }
    }

    public static boolean inputUD(String msg) {
        String input = getInput(msg);
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Cannot be empty");
        }
        if (input.equalsIgnoreCase("U")) {
            return true;
        } else if (input.equalsIgnoreCase("D")) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid, please input again!");
        }
    }

    public static String inputString(String msg, String regex) {
        String input = getInput(msg);
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("Invalid, please input again!");
        }
        return input;
    }

    public static boolean inputPhone(String msg) {
        String input = getInput(msg);
        if (!input.matches("^0[0-9]{9}$")) {
            throw new IllegalArgumentException("Invalid, please input again!");
        } else {
            return true;
        }
    }

    public static boolean inputEmail(String msg) {
        String input = getInput(msg);
        if (!input.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$")) {
            throw new IllegalArgumentException("Invalid, please input again!");
        } else {
            return true;
        }
    }

    public static String inputNameNormalize(String msg, String regex) {
        String name = inputString(msg, regex).trim().toLowerCase();
        String[] normalizeName = name.split("\\s+");
        StringBuilder nameBuilder = new StringBuilder();
        for (String temp : normalizeName) {

            nameBuilder.append(Character.toUpperCase(temp.charAt(0)))
                    .append(temp.substring(1)).append(" ");

        }
        return nameBuilder.toString().trim();
    }

    public static String inputCourseName(String msg) {
        String input = getInput(msg);
        return switch (input.toLowerCase()) {
            case "java" -> "Java";
            case ".net" -> ".Net";
            case "c/c++" -> "C/C++";
            default -> throw new IllegalArgumentException("Invalid, please input again!");
        };
    }
}