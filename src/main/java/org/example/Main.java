package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello world! Welcome to the Bank of Matt!");
        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == -1) {
                System.out.println("Exiting...");
                break;
            } else if (choice == 1) {
                selectAccount();
            } else if (choice == 2) {
                createAccount();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        BankAccount newAccount = new BankAccount();
        accounts.add(newAccount);
        System.out.println("Account created: " + newAccount);
        mainMenu(newAccount);
    }

    private static void selectAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine());

        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            mainMenu(account);
        } else {
            System.out.println("Account doesn't exist.");
        }
    }

    private static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private static void mainMenu(BankAccount account) {
        System.out.println("Hello " + account.getAccountHolderName() + "!");
        while (true) {
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdraw");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit.");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                System.out.println("Exiting to main menu...");
                break;
            } else if (choice == 1) {
                System.out.println("Current Balance: $" + account.getBalance());
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double amount = Double.parseDouble(scanner.nextLine());
                account.withdraw(amount);
            } else if (choice == 3) {
                System.out.print("Enter amount to deposit: ");
                double amount = Double.parseDouble(scanner.nextLine());
                account.deposit(amount);
            } else if (choice == 4) {
                System.out.print("Please enter the account number to transfer to (0 to cancel): ");
                int toAccountNumber = Integer.parseInt(scanner.nextLine());
                if (toAccountNumber == 0) {
                    System.out.println("Transfer cancelled.");
                    continue;
                }
                BankAccount toAccount = findAccount(toAccountNumber);
                if (toAccount != null) {
                    System.out.print("Please enter the amount to transfer: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    account.transfer(toAccount, amount);
                    System.out.println("The name on the account is: " + account.getAccountHolderName() + " and they have a balance of $" + account.getBalance());
                    System.out.println("The name on the account is: " + toAccount.getAccountHolderName() + " and they have a balance of $" + toAccount.getBalance());
                } else {
                    System.out.println("Account doesn't exist.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
