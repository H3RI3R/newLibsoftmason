package com.library.app;

import com.library.menu.StudentMenu;

public class main {

    public static void main(String[] args) {

        // Create StudentMenu object
        StudentMenu studentMenu = new StudentMenu();

        // Open Student Management Menu
        studentMenu.showMenu();

        // Runs when Student Menu is closed
        System.out.println("Program closed.");
    }
}