import java.util.*;

class Account {
    String name;
    double balance;

    Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static LinkedHashMap<Integer, Account> accounts = new LinkedHashMap<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n----- BANKING SYSTEM -----");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
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
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        accounts.put(accountNumber, new Account(name, balance));

        System.out.println("Account created successfully!");
    }

    static void deposit() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        if (accounts.containsKey(accountNumber)) {

            System.out.print("Enter Deposit Amount: ");
            double amount = sc.nextDouble();

            Account account = accounts.get(accountNumber);
            account.balance += amount;

            System.out.println("Amount deposited successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    static void withdraw() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        if (accounts.containsKey(accountNumber)) {

            System.out.print("Enter Withdrawal Amount: ");
            double amount = sc.nextDouble();

            Account account = accounts.get(accountNumber);

            if (amount <= account.balance) {
                account.balance -= amount;
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance!");
            }

        } else {
            System.out.println("Account not found!");
        }
    }

    static void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        if (accounts.containsKey(accountNumber)) {

            Account account = accounts.get(accountNumber);

            System.out.println("Account Holder: " + account.name);
            System.out.println("Current Balance: " + account.balance);

        } else {
            System.out.println("Account not found!");
        }
    }

    static void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n----- ALL ACCOUNTS -----");

        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {

            System.out.println("Account Number: " + entry.getKey());
            System.out.println("Name: " + entry.getValue().name);
            System.out.println("Balance: " + entry.getValue().balance);
            System.out.println("----------------------");
        }
    }
}