package org.example;

import java.util.Scanner;

public class BankAccount {
    private double balance;
    private String accountHolderName;
    private int accountNumber;
    private static int accountCounter = 1000; // To generate unique account numbers

    // Constructor with all parameters
    public BankAccount(String accountHolderName, double balance, int accountNumber) {
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    // Constructor that generates a unique account number
    public BankAccount(String accountHolderName, double balance) {
        this(accountHolderName, balance, accountCounter++);
    }

    // Constructor that takes no parameters and allows user input
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's make a new account!");
        System.out.print("What is the name for the account? ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = Double.parseDouble(scanner.nextLine());
        this.accountNumber = accountCounter++;
    }

    // Getter methods
    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // Transfer method
    public void transfer(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred $" + amount + " from account " + this.accountNumber + " to account " + toAccount.getAccountNumber());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " Account Holder: " + accountHolderName + " Balance: $" + balance;
    }
}
