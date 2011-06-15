package com.restbucks.ordering.domain;

import org.joda.time.DateTime;

public class Payment {

    private final double amount;
    private final String cardholderName;
    private final String cardNumber;
    private final int expiryMonth;
    private final int expiryYear;
    private DateTime paymentDate;

    public Payment(double amount, String cardholderName, String cardNumber, int expiryMonth, int expiryYear) {
        this.amount = amount;
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        paymentDate = new DateTime();

    }
    public double getAmount() {
        return amount;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }
    public DateTime getPaymentDate() {
        return paymentDate;
    }
}
