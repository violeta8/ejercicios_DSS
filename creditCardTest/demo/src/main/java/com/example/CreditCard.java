package com.example;

import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalStateException;



public class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private int cvv;
    private double balance;
    private List<Double> transactions = new ArrayList<>();
    private List<String> movements = new ArrayList<>();

    public CreditCard(String cardNumber, String cardHolderName, String expirationDate, int cvv, double balance) {

        //comprobar que el número de tarjeta es correcto
        if (cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must have 16 digits");
        }
        //comprobar que el nombre del titular no está vacío
        if (cardHolderName.isEmpty()) {
            throw new IllegalArgumentException("Card holder name cannot be empty");
        }
        //comprobar que el nombre del titular es correcto
        if (!cardHolderName.matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Card holder name must contain only letters and spaces");
        }
        //comprobar que la fecha de caducidad es correcta
        if (!expirationDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
            throw new IllegalArgumentException("Expiration date must be in format MM/YY");
        }
        //comprobar que el cvv es correcto
        if (cvv < 100 || cvv > 999) {
            throw new IllegalArgumentException("CVV must be a 3-digit number");
        }
        //comprobar que el saldo es correcto
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = (expirationDate);
        this.cvv = cvv;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public double getBalance() {
        return balance;
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public List<String> getMovements() {
        return movements;
    }

    public double getTransactionTotal() {
        double total = 0;
        for (double transaction : transactions) {
            total += transaction;
        }
        return total;
    }



    public void printMovements() {
        for (String movement : movements) {
            System.out.println(movement);
        }
    }

    public void printTransactions() {
        for (double transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void makeTransaction(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Transaction amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(amount);
        movements.add(String.format("Transaction: -%.2f", amount));
    }

    public void makePayment(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Payment amount must be positive");
        }
        balance += amount;
        transactions.add(-amount);
        movements.add(String.format("Payment: +%.2f", amount));
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Amount must be positive");
        }
        balance += amount;
        movements.add(String.format("Funds added: +%.2f", amount));
    }
    
    
}
