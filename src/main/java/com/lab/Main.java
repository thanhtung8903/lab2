package com.lab;

import com.lab.validation.Validation;
import com.lab.view.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
        Validation.inputChoice("Enter your choice: ", 1, 5);
    }
}
