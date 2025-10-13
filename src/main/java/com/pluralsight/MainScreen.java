package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MainScreen {
    Scanner scanner = new Scanner(System.in);
    FileHandling fileHandler = new FileHandling();
    LedgerScreen ledgerScreen = new LedgerScreen();

    public void showMainScreen() {
        // Run the MainScreen menu in while() loop - will run until the user exits the program
        while (true) {
            System.out.println("WELCOME TO THE JENIDUB LEDGER OF DOOM!");
            System.out.println("Use at your own risk....");

            // Display the Home Screen menu
            System.out.println("""
                \n*** HOME SCREEN MENU ***
                [D] Add Deposit
                [P] Make Payment (Debit)
                [L] Ledger - display the ledger
                [X] Exit From the Program
            """);

            // Use scanner to store user selection in variable
            System.out.println("Please select an option from the menu:    ");
            String option = scanner.nextLine();

            // Run option the user selected - change input toLowerCase for easier matching
            switch(option.toLowerCase()) {
                case "d":
                    System.out.println("*** ADD A DEPOSIT ***");
                    // create Transaction instance from user inputs
                    Transaction newDeposit = addNewDeposit();
                    // convert Transaction to a string to add to transactions.csv
                    String transactionStringD = newDeposit.toString();
                    // write the new transaction string to the file
                    fileHandler.writeTransaction(transactionStringD);
                    break;

                case "p":
                    System.out.println("*** ADD A PAYMENT ***");
                    // create Transaction instance from user inputs
                    Transaction newPayment = addNewPayment();
                    // convert Transaction to a string to add to transactions.csv
                    String transactionStringP = newPayment.toString();
                    // write the new transaction string to the file
                    fileHandler.writeTransaction(transactionStringP);
                    break;

                case "l":
                    System.out.println("Switching to the ledger menu...");
                    // switch to the LedgerScreen class - new set of options
                    ledgerScreen.showLedgerMenu();
                    break;

                case "x":
                    System.out.println("Thank you for using the JeniDub Ledger! See you next time");
                    System.out.println("Exiting program....");
                    // use return statement to close the program
                    return;

                default:
                    // user did not enter one of the available selections - prompt them to try again
                    System.out.println("Invalid option - please try again!");
            }
        }
    }

    public static Transaction addNewDeposit() {
        // create a new Scanner instance to take in user information
        Scanner scanner = new Scanner(System.in);

        // get now() info
        LocalDate todayDate = LocalDate.now();
        LocalTime todayTime = LocalTime.now();

        // Prompt user for all info required for the Transaction class
        System.out.println("Please describe the deposit and hit enter:   ");
        String description = scanner.nextLine();
        System.out.println("Please enter the vendor name:   ");
        String vendor = scanner.nextLine();
        System.out.println("Amount of the transaction (use following format XX.XX - no punctuation):   ");
        float amount = Float.parseFloat(scanner.nextLine());

        // return Transaction instance using all user inputs
        return new Transaction(todayDate, todayTime, description, vendor, amount);
    }

    public static Transaction addNewPayment() {
        // create a new Scanner instance to take in user information
        Scanner scanner = new Scanner(System.in);

        // get now() info
        LocalDate todayDate = LocalDate.now();
        LocalTime todayTime = LocalTime.now();

        // Prompt user for all info required for the Transaction class
        System.out.println("Please describe the payment and hit enter:   ");
        String description = scanner.nextLine();
        System.out.println("Please enter the vendor name:   ");
        String vendor = scanner.nextLine();
        System.out.println("Amount of the transaction (use following format XX.XX - no punctuation):   ");
        float amount = -1 * Float.parseFloat(scanner.nextLine());

        // return Transaction instance using all user inputs
        return new Transaction(todayDate, todayTime, description, vendor, amount);
    }
}
