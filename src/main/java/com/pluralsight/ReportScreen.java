package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class ReportScreen {
    // Create new Scanner instance for user input
    Scanner scanner = new Scanner(System.in);

    // Show Report Screen and handle user choices
    public void showReportScreen(ArrayList<Transaction> transactionList) {
        while (true) {
            // REPORT MENU
            //        § 1) Month To Date
            //        § 2) Previous Month
            //        § 3) Year To Date
            //        § 4) Previous Year
            //        § 5) Search by Vendor - prompt the user for the vendor name
            //        and display all entries for that vendor
            //        § 0) Back - go back to the Ledger page
            System.out.println("*** REPORT MENU ***");
            System.out.println("""
             [1] Month to Date
             [2] Previous Month
             [3] Year to Date
             [4] Previous Year
             [5] Search by Vendor
             [0] Back to the Home Menu
            """);

            // Get user selection and collect with scanner
            System.out.println("Please make your selection now:    ");
            String input = scanner.nextLine();

            // Run option the user selected - change input toLowerCase for easier matching
            switch(input.toLowerCase()) {
                case "1":
                    System.out.println("Display all transactions in the current month");
                    break;
                case "2":
                    System.out.println("Display all transactions in the previous month");
                    break;
                case "3":
                    System.out.println("Display all transactions in the current year");
                    break;
                case "4":
                    System.out.println("Display all transactions in the previous year");
                    break;
                case "5":
                    System.out.println("Search by vendor and show matching transactions");
                    break;
                case "0":
                    System.out.println("Return to the Ledger Screen");
                    return;
                default:
                    System.out.println("Invalid choice - please try again");
                    break;
            }
        }
    }
}
