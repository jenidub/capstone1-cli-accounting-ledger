package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    // property variables
    LocalDate date;
    LocalTime time;
    String description;
    String vendor;
    float amount;

    // constructor
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    // methods
    public String toString() {
        // 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
        String formattedDate = getDate().toString();
        LocalTime time = getTime();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormat);

        return String.format("%s|%s|%s|%s|$%.2f",
                formattedDate,
                formattedTime,
                getDescription(),
                getVendor(),
                getAmount()
        );
    }
}
