package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandling {
    public FileHandling() {}

    // *** For MainScreen Class => write new transaction string from MainScreen to the transactions.csv file
    public void writeTransaction(String newTransaction) {
        /// CODE TAKEN DIRECTLY FROM WORKBOOK 3
        try {
            // create a FileWriter object connected to transactions.csv file - used absolute reference of the file
            FileWriter fileWriter = new FileWriter("/Users/missjeni/pluralsight/capstone-1-ledger/src/main/resources/transactions.csv");

            // create a BufferedWriter
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // write newTransaction (string with formatted transaction information) to the file
            bufWriter.write(newTransaction);

            // close the writer
            bufWriter.close();
        }

        catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.getStackTrace();
        }
    }

    // *** For LedgerScreen Class => read the csv file and create ArrayList of Transactions
    public ArrayList<Transaction> readTransactionFile() {
        // create an ArrayList<Transaction> to store file info
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>(1000);

        /// CODE STRUCTURE TAKEN DIRECTLY FROM WORKBOOK 3
        try {
            // create a FileReader object connected to transactions.csv file - used absolute reference of the file
            FileReader fileReader = new FileReader("/Users/missjeni/pluralsight/capstone-1-ledger/src/main/resources/transactions.csv");

            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;

            // read until there is no more data
            while((input = bufReader.readLine()) != null) {
                // To create a Transaction List for the LedgerScreen, you need to take each line from the file
                // and convert it into an ArrayList of Transaction instances

                // To create each Transaction instance, you will split the input variable by "|"
                // Creates a String array with all info - access by index
                String[] transactionInfo = input.split("\\|");

                // ORDER of INFORMATION (from Capstone Workbook):
                // 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50

                // Assign each value in transactionInfo by index then parse as needed to correct data type
                LocalDate date = LocalDate.parse(transactionInfo[0]); // index[0] => convert/parse from String to LocalDate
                LocalTime time = LocalTime.parse(transactionInfo[1]); // index[1] => convert/parse from String to LocalTime
                String description = transactionInfo[2]; // index[2] => no conversion needed
                String vendor = transactionInfo[3]; // index[3] => no conversion needed
                float amount = Float.parseFloat(transactionInfo[4]); // index[4] => convert/parse from String to float

                // Create Transaction instance with all information
                Transaction transaction = new Transaction(date, time, description, vendor, amount);

                // Add transaction to the transactionList
                transactionList.add(transaction);
            }

            // close the stream and release the resources
            bufReader.close();

        }

        catch(IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }

        // return ArrayList<Transaction> transactionList to the LedgerScreen for use
        return transactionList;
    }
}
