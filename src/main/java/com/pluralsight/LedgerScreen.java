package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class LedgerScreen {
    // empty constructor [ not required but can be included ]
    //    public LedgerScreen(){}

    // Add a Scanner instance for user input
    Scanner scanner = new Scanner(System.in);

    // Add an instance of the FileHandler for printouts
    FileHandling fileHandler = new FileHandling();

    // Get all transactions from the transactions.csv file into an ArrayList
    ArrayList<Transaction> transactionList = fileHandler.readTransactionFile();

    public void showLedgerMenu() {
        // Run the menu in while() loop - will run until the user exits to the MainScreen
        while (true) {
            //Ledger - All entries should show the newest entries first
            //        o A) All - Display all entries
            //        o D) Deposits - Display only the entries that are deposits into the
            //        account
            //        o P) Payments - Display only the negative entries (or payments)
            //        o H) Home - go back to the home page
            System.out.println("*** LEDGER MENU ***");
            System.out.println("""
             [A] All - Display all entries
             [D] Deposits - Display only the entries that are deposits into the account
             [P] Payments - Display only the negative entries (or payments)
             [R] Reports -  New Screen run pre-defined reports
             [H] Back to the Home Menu
            """);

            // Get user selection and collect with scanner
            System.out.println("Please make your selection now:    ");
            String input = scanner.nextLine();

            // Run option the user selected - change input toLowerCase for easier matching
            switch(input.toLowerCase()) {
                case "a":
                    System.out.println("Display all entries (most recent first)");
                    break;
                case "d":
                    System.out.println("Display all deposits / positive entries (most recent first)");
                    break;
                case "p":
                    System.out.println("Display all payments / negative entries (most recent first)");
                    break;
                case "r":
                    System.out.println("Reports Menu (new screen)");
                    break;
                case "h":
                    System.out.println("Return to the home screen...");
                    break;
                default:
                    System.out.println("Invalid choice - please try again");
                    break;
            }
        }
    }
}
