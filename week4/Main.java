import java.util.Scanner;
import java.util.TreeMap;

class Account {

    private int accountNumber;
    private String customerName;
    private double balance;

    public Account(int accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Amount Deposited Successfully!");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance = balance - amount;
            System.out.println("Amount Withdrawn Successfully!");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void displayAccount() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer Name : " + customerName);
        System.out.println("Balance        : " + balance);
        System.out.println("-----------------------------");
    }
}

public class Main {

    static TreeMap<Integer, Account> accounts = new TreeMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account Number Already Exists!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Invalid Balance!");
            return;
        }

        Account account =
                new Account(accountNumber, customerName, balance);

        accounts.put(accountNumber, account);

        System.out.println("Account Created Successfully!");
    }

    public static void depositMoney() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account Not Found!");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount!");
        } else {
            account.deposit(amount);
        }
    }

    public static void withdrawMoney() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account Not Found!");
            return;
        }

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount!");
        } else {
            account.withdraw(amount);
        }
    }

    public static void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account Not Found!");
        } else {
            System.out.println("Current Balance: " +
                    account.getBalance());
        }
    }

    public static void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available!");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");
        System.out.println("Accounts are displayed in sorted order.\n");

        for (Account account : accounts.values()) {
            account.displayAccount();
        }
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== BANKING SYSTEM =====");

            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    viewAllAccounts();
                    break;

                case 6:
                    System.out.println("Thank You For Using Banking System!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}