package com.pluralsight;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerScreen {
    // Add a Scanner instance for user input
    Scanner scanner = new Scanner(System.in);

    //Create instances of ReportScreen so we can switch screens
    ReportScreen reportScreen = new ReportScreen();

    public void showLedgerMenu(ArrayList<Transaction> transactionList) {
        // Run the menu in while() loop - will run until the user exits to the MainScreen
        while (true) {
            //Ledger - All entries should show the newest entries first
            //        o A) All - Display all entries
            //        o D) Deposits - Display only the entries that are deposits into the
            //        account
            //        o P) Payments - Display only the negative entries (or payments)
            //        o H) Home - go back to the home page
            System.out.println("\n\n*** LEDGER MENU ***");
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
                    displayAllTransactions(transactionList);
                    break;
                case "d":
                    displayAllDeposits(transactionList);
                    break;
                case "p":
                    displayAllPayments(transactionList);
                    break;
                case "r":
                    reportScreen.showReportScreen(transactionList);
                    break;
                case "h":
                    System.out.println("Returning to the HOME SCREEN...\n");
                    return;
                default:
                    System.out.println("Invalid choice - please try again");
                    break;
            }
        }
    }

    // Display all transactions - most recent first
    public void displayAllTransactions(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== FULL TRANSACTION LIST (Most Recent First) ===");

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            System.out.println(transactionList.get(i).toString());
        }
    }

    // Display all deposits only - most recent first
    public void displayAllDeposits(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== LEDGER DEPOSIT LIST (Most Recent First) ===");

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getAmount() >= 0) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    // Display all payments only - most recent first
    public void displayAllPayments(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== LEDGER PAYMENT LIST (Most Recent First) ===");

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getAmount() < 0) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }
}
