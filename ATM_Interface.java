package Task_2;

import java.util.Scanner;

// Class representing the Bank Account
class BankAccount {
    private double balance;

    // Constructor to set initial balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Rs " + amount + " deposited successfully!");
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Rs " + amount + " withdrawn successfully!");
            } else {
                System.out.println("Insufficient balance. Your current balance is Rs " + balance);
            }
        } else {
            System.out.println("Withdrawal amount must be greater than 0.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Your current balance is: Rs " + balance);
    }
}

// Class representing the ATM
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    // Method to display the ATM menu
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n====== Welcome to ATM ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: Rs ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: Rs ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        } while (choice != 4);
    }
}

// Main class
public class ATM_Interface {
    public static void main(String[] args) {
        // Create a bank account with initial balance Rs 5000
        BankAccount myAccount = new BankAccount(5000);

        // Create ATM with this bank account
        ATM atm = new ATM(myAccount);

        // Show ATM menu
        atm.showMenu();
    }
}
