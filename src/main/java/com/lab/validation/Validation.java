package com.lab.validation;

import java.util.Scanner;

public class Validation {

    static Scanner sc = new Scanner(System.in);

    private static String getInput(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public static int inputChoice(String msg, int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(getInput(msg));
                if (choice < min || choice > max) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.err.println("Invalid, please input again!");
            }
        }
    }

    public static boolean inputYesNO(String msg) {
        while (true) {
            String input = getInput(msg);
            if (!input.isEmpty()) {
                if (input.equalsIgnoreCase("Y")) {
                    return true;
                } else if (input.equalsIgnoreCase("N")) {
                    return false;
                }
                System.err.println("You need input Y/y or N/n");
            } else {
                System.err.println("You need input Y/y or N/n, cannot empty");
            }
        }
    }

    public static boolean inputUD(String msg) {
        while (true) {
            String input = getInput(msg);
            if (input.isEmpty()) {
                System.err.println("Cannot empty");
            } else {
                if (input.equalsIgnoreCase("U")) {
                    return true;
                } else if (input.equalsIgnoreCase("D")) {
                    return false;
                } else {
                    System.err.println("Invalid, please input again!");
                }
            }
        }
    }

    public static String inputString(String msg, String regex) {
        while (true) {
            String input = getInput(msg);
            if (input.matches(regex)) {
                return input;
            }
            System.err.println("Invalid, please input again!");
        }
    }

    public static String inputNameNormalize(String msg, String regex) {
        String name = inputString(msg, regex).trim().toLowerCase();
        String[] normalizeName = name.split("\\s+");
        StringBuilder nameBuilder = new StringBuilder();
        for (String temp : normalizeName) {
            if (temp.length() > 0) {
                nameBuilder.append(Character.toUpperCase(temp.charAt(0)))
                        .append(temp.substring(1)).append(" ");
            }
        }
        return nameBuilder.toString().trim();
    }

    public static String inputCourseName(String msg) {
        while (true) {
            String input = getInput(msg);
            switch (input.toLowerCase()) {
                case "java":
                    return "Java";
                case ".net":
                    return ".Net";
                case "c/c++":
                    return "C/C++";
                default:
                    System.err.println("Invalid, please input again!");
            }
        }
    }
}
