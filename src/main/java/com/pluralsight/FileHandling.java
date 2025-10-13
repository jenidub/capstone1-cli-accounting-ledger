package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileHandling {
    public FileHandling() {}

    public void writeTransaction(String newTransaction) {
        try {
            // create a FileWriter
            FileWriter fileWriter = new FileWriter("/Users/missjeni/pluralsight/capstone-1-ledger/src/main/resources/transactions.csv");

            // create a BufferedWriter
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // write to the file
            bufWriter.write(newTransaction);

            // close the writer
            bufWriter.close();
        }

        catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.getStackTrace();
        }
    }

    public ArrayList<Transaction> readTransactionFile() {
        // create an ArrayList<Transaction> to store file info
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>(1000);

        try {
            // create a FileReader object connected to the File
            FileReader fileReader = new FileReader("/Users/missjeni/pluralsight/capstone-1-ledger/src/main/resources/transactions.csv");

            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;

            // read until there is no more data
            while((input = bufReader.readLine()) != null) {
                // break up the line into the transaction info by "|"
                // 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
                String[] transactionInfo = input.split("\\|");
                LocalDate date = LocalDate.parse(transactionInfo[0]);
                LocalTime time = LocalTime.parse(transactionInfo[1]);
                String description = transactionInfo[2];
                String vendor = transactionInfo[3];
                float amount = Float.parseFloat(transactionInfo[4]);
                transactionList.add(new Transaction(date, time, description, vendor, amount));
            }

            // close the stream and release the resources
            bufReader.close();

        }

        catch(IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }

        // return the transaction list as an ArrayList
        return transactionList;
    }
}
