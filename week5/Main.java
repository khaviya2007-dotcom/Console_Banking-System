import java.io.*;
import java.util.*;

class Account {

    int accountNumber;
    String customerName;
    double balance;

    Account(int accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public String toFileString() {
        return accountNumber + "," + customerName + "," + balance;
    }

    public void display() {
        System.out.println("----------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Balance        : " + balance);
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static String fileName = "accounts.txt";

    static ArrayList<Account> accounts = new ArrayList<>();


    public static void loadAccounts() {

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }

            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int accountNumber = Integer.parseInt(data[0]);
                String customerName = data[1];
                double balance = Double.parseDouble(data[2]);

                accounts.add(new Account(
                        accountNumber,
                        customerName,
                        balance));
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error loading accounts.");

        }
    }


    public static void saveAccounts() {

        try {

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(fileName));

            for (Account account : accounts) {

                bw.write(account.toFileString());

                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {

            System.out.println("Error saving accounts.");

        }
    }


    public static Account findAccount(int accountNumber) {

        for (Account account : accounts) {

            if (account.accountNumber == accountNumber) {

                return account;
            }
        }

        return null;
    }


    public static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        sc.nextLine();

        if (findAccount(accountNumber) != null) {

            System.out.println("Account Already Exists!");

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

        accounts.add(new Account(
                accountNumber,
                customerName,
                balance));

        saveAccounts();

        System.out.println("Account Created Successfully!");
    }


    public static void deposit() {

        System.out.print("Enter Account Number: ");

        int accountNumber = sc.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {

            System.out.println("Account Not Found!");

            return;
        }

        System.out.print("Enter Deposit Amount: ");

        double amount = sc.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid Amount!");

            return;
        }

        account.balance = account.balance + amount;

        saveAccounts();

        System.out.println("Amount Deposited Successfully!");
    }


    public static void withdraw() {

        System.out.print("Enter Account Number: ");

        int accountNumber = sc.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {

            System.out.println("Account Not Found!");

            return;
        }

        System.out.print("Enter Withdrawal Amount: ");

        double amount = sc.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid Amount!");

        } else if (amount > account.balance) {

            System.out.println("Insufficient Balance!");

        } else {

            account.balance = account.balance - amount;

            saveAccounts();

            System.out.println("Amount Withdrawn Successfully!");
        }
    }


    public static void checkBalance() {

        System.out.print("Enter Account Number: ");

        int accountNumber = sc.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {

            System.out.println("Account Not Found!");

        } else {

            System.out.println(
                    "Current Balance: " + account.balance);
        }
    }


    public static void viewAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No Accounts Available!");

            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Account account : accounts) {

            account.display();
        }
    }


    public static void main(String[] args) {

        loadAccounts();

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
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    viewAllAccounts();
                    break;

                case 6:
                    System.out.println(
                            "Thank You For Using Banking System!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}