import java.util.ArrayList;
import java.util.Scanner;

class Account {
    long accountNumber;
    String name;
    double balance;

    Account(long accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
}

public class Main {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void createAccount() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();

        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();

        accounts.add(new Account(number, name, balance));

        System.out.println("Account created successfully!");
    }

    static void deposit() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();

        for (Account a : accounts) {
            if (a.accountNumber == number) {
                System.out.print("Enter Deposit Amount: ");
                double amount = sc.nextDouble();

                if (amount > 0) {
                    a.balance += amount;
                    System.out.println("Amount deposited successfully!");
                    System.out.println("Current Balance: " + a.balance);
                } else {
                    System.out.println("Invalid amount!");
                }
                return;
            }
        }

        System.out.println("Account not found!");
    }

    static void withdraw() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();

        for (Account a : accounts) {
            if (a.accountNumber == number) {
                System.out.print("Enter Withdrawal Amount: ");
                double amount = sc.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount!");
                } else if (amount > a.balance) {
                    System.out.println("Insufficient balance!");
                } else {
                    a.balance -= amount;
                    System.out.println("Amount withdrawn successfully!");
                    System.out.println("Current Balance: " + a.balance);
                }
                return;
            }
        }

        System.out.println("Account not found!");
    }

    static void checkBalance() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();

        for (Account a : accounts) {
            if (a.accountNumber == number) {
                System.out.println("Account Holder: " + a.name);
                System.out.println("Balance: " + a.balance);
                return;
            }
        }

        System.out.println("Account not found!");
    }

    static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n--- All Accounts ---");

        for (Account a : accounts) {
            System.out.println("Account Number: " + a.accountNumber);
            System.out.println("Name: " + a.name);
            System.out.println("Balance: " + a.balance);
            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    displayAccounts();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}