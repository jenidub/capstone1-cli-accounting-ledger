package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>(100);

        // Read the transactions.csv file and save it in the ArrayList

        // run the Home Screen in a while loop
        while (true) {
            // Display the Home Screen menu
            System.out.println("""
                *** WELCOME TO THE HOME SCREEN ***
                *** MENU ***
                [D] Add Deposit
                [P] Make Payment (Debit)
                [L] Ledger - display the ledger
                [X] Exit From the Program
            """);
            System.out.println("Please select an option from the menu:    ");
            String option = scanner.nextLine();

            // Check the selection and run the steps for each
            switch(option) {
                case "D":
                    System.out.println("Add a deposit to the ledger");
                    break;
                case "P":
                    System.out.println("Add a payment to the ledger");
                    break;
                case "L":
                    System.out.println("View the ledger menu");
                    break;
                case "X":
                    System.out.println("Thank you for using the JeniDub Ledger! See you next time");
                    System.out.println("Exiting program....");
                    return;
                default:
                    System.out.println("Invalid option - please try again!");
            }
        }
    }
}