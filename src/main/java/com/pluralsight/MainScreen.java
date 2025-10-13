package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MainScreen {
    Scanner scanner = new Scanner(System.in);
    FileHandling fileHandler = new FileHandling();
    LedgerScreen ledgerScreen = new LedgerScreen();

    public void showMainScreen() {
        // run the Home Screen in a while loop
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

            // Check the selection and run the steps for each
            switch(option.toLowerCase()) {
                case "d":
                    System.out.println("*** ADD A DEPOSIT ***");
                    Transaction newDeposit = addNewDeposit();
                    String transactionStringD = newDeposit.toString();
                    fileHandler.writeTransaction(transactionStringD);
                    break;

                case "p":
                    System.out.println("*** ADD A PAYMENT ***");
                    Transaction newPayment = addNewPayment();
                    String transactionStringP = newPayment.toString();
                    System.out.println(transactionStringP);
                    fileHandler.writeTransaction(transactionStringP);
                    break;

                case "l":
                    System.out.println("Switching to the ledger menu...");
//                    ledgerScreen.showLedgerMenu();
                    break;

                case "x":
                    System.out.println("Thank you for using the JeniDub Ledger! See you next time");
                    System.out.println("Exiting program....");
                    return;

                default:
                    System.out.println("Invalid option - please try again!");
            }
        }
    }

    public static Transaction addNewDeposit() {
        Scanner scanner = new Scanner(System.in);

        // get today's info
        LocalDate todayDate = LocalDate.now();
        LocalTime todayTime = LocalTime.now();

        System.out.println("Please describe the deposit and hit enter:   ");
        String description = scanner.nextLine();
        System.out.println("Please enter the vendor name:   ");
        String vendor = scanner.nextLine();
        System.out.println("Amount of the transaction (use following format XX.XX - no punctuation):   ");
        float amount = Float.parseFloat(scanner.nextLine());
        return new Transaction(todayDate, todayTime, description, vendor, amount);
    }

    public static Transaction addNewPayment() {
        Scanner scanner = new Scanner(System.in);

        // get today's info
        LocalDate todayDate = LocalDate.now();
        LocalTime todayTime = LocalTime.now();

        System.out.println("Please describe the payment and hit enter:   ");
        String description = scanner.nextLine();
        System.out.println("Please enter the vendor name:   ");
        String vendor = scanner.nextLine();
        System.out.println("Amount of the transaction (use following format XX.XX - no punctuation):   ");
        float amount = -1 * Float.parseFloat(scanner.nextLine());
        return new Transaction(todayDate, todayTime, description, vendor, amount);
    }
}
