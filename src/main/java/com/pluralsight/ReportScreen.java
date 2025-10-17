package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
             [6] Custom Search
             [0] Back to the Home Menu
            """);

            // Get user selection and collect with scanner
            System.out.println("Please make your selection now:    ");
            String input = scanner.nextLine();

            // Run option the user selected - change input toLowerCase for easier matching
            switch(input.toLowerCase()) {
                case "1":
                    System.out.println("Display all transactions in the current month");
                    displayCurrentMonth(transactionList);
                    break;
                case "2":
                    System.out.println("Display all transactions in the previous month");
                    displayPreviousMonth(transactionList);
                    break;
                case "3":
                    System.out.println("Display all transactions in the current year");
                    displayCurrentYear(transactionList);
                    break;
                case "4":
                    System.out.println("Display all transactions in the previous year");
                    displayPreviousYear(transactionList);
                    break;
                case "5":
                    System.out.println("Search by vendor and show matching transactions");
                    displayByVendor(transactionList);
                    break;
                case "6":
                    System.out.println("Custom Search [CP1 Challenge Yourself]");
                    customSearch(transactionList);
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

    public void displayCurrentMonth(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== TRANSACTIONS IN THE CURRENT MONTH (Most Recent First) ===");
        // Get today's month
        Month currentMonth = LocalDateTime.now().getMonth();
        System.out.println(currentMonth);

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getDate().getMonth() == currentMonth) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    public void displayPreviousMonth(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== TRANSACTIONS IN THE PREVIOUS MONTH (Most Recent First) ===");

        // Get previous month
        Month previousMonth = LocalDateTime.now().minusMonths(1).getMonth();

        System.out.println(previousMonth);

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getDate().getMonth() == previousMonth) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    public void displayCurrentYear(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== TRANSACTIONS IN THE CURRENT YEAR (Most Recent First) ===");
        // Get today's month
        int currentYear = LocalDateTime.now().getYear();
        System.out.println(currentYear);

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getDate().getYear() == currentYear) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    public void displayPreviousYear(ArrayList<Transaction> transactionList){
        System.out.println("\n\n=== TRANSACTIONS IN THE PREVIOUS YEAR (Most Recent First) ===");
        // Get previous year
        int previousYear = LocalDateTime.now().minusYears(1).getYear();

        System.out.println(previousYear);

        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getDate().getYear() == previousYear) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    public void displayByVendor(ArrayList<Transaction> transactionList){
        System.out.println("What vendor would you like to search for?   ");
        String vendorName = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n\n=== VENDOR SEARCH TRANSACTIONS LIST (Most Recent First) ===");
        for (var i = transactionList.size() - 1; i >= 0; i--) {
            if (transactionList.get(i).getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }

    public void customSearch(ArrayList<Transaction> transactionList) {
        // Create a copy of the transactionList to be filtered
        List<Transaction> filteredList = transactionList;

        // Prompt the user for the following search values:
        //§ Start Date
        System.out.println("Start Date (YYYY-MM-DD):   ");
        // Check if the user enters anything or if it's blank
        String startDateString = scanner.nextLine();

        // Filter out transaction that are before the start date if not blank
        if (!startDateString.isEmpty()) {
            LocalDate startDate = LocalDate.parse(startDateString);
            filteredList = filteredList.stream()
                    .filter(transaction -> transaction.getDate().isAfter(startDate) || transaction.getDate().isEqual(startDate))
                    .collect(Collectors.toList());
        }

        //§ End Date
        System.out.println("End Date (YYYY-MM-DD):   ");
        // Check if the user enters anything or if it's blank
        String endDateString = scanner.nextLine();
        if (!endDateString.isEmpty()) {
            LocalDate endDate = LocalDate.parse(endDateString);
            filteredList = filteredList.stream()
                    .filter(transaction -> transaction.getDate().isBefore(endDate) || transaction.getDate().isEqual(endDate))
                    .collect(Collectors.toList());
        }

        //§ Description
        System.out.println("Description:   ");
        // Check if the user enters anything or if it's blank
        String description = scanner.nextLine().toLowerCase();

        if (!description.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(transaction -> transaction.getDescription().toLowerCase().contains(description))
                    .collect(Collectors.toList());
        }

        //§ Vendor
        System.out.println("Vendor Name:   ");
        // Check if the user enters anything or if it's blank
        String vendor = scanner.nextLine().toLowerCase();

        if (!vendor.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(transaction -> transaction.getVendor().toLowerCase().contains(vendor))
                    .collect(Collectors.toList());
        }

        //§ Amount
        System.out.println("Amount (XXX.XX):   ");

        // Check if the user enters anything or if it's blank
        String amount = scanner.nextLine();

        if (!amount.isEmpty()) {
            double amountDouble = Double.parseDouble(amount);
            filteredList = filteredList.stream()
                    .filter(transaction -> transaction.getAmount() == amountDouble)
                    .collect(Collectors.toList());
        }

        // Print out the results of the filtering process
        // If the list is blank, print out a message informing the user
        // Else iterate through the filtered list and print out each transaction
        System.out.println("\n\n=== CUSTOM SEARCH TRANSACTION LIST (Most Recent First) ===");
        if (filteredList.isEmpty()) {
            System.out.println("***  No transactions matched your search  ***");
        } else {
            for (var i = filteredList.size() - 1; i >= 0; i--) {
                System.out.println(filteredList.get(i).toString());
            }
        }
    }

}
