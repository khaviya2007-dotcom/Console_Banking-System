import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds for this withdrawal.");
            return false;
        }
        balance -= amount;
        System.out.printf("Successfully withdrew $%.2f. New Balance: $%.2f%n", amount, balance);
        return true;
    }
}
public class Main {
    private static Map<Integer, Account> accountMap = new HashMap<>();
    private static int accountNumberCounter = 1001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== HashMap Banking System ===");

        while (running) {
            System.out.println("\n-----------------------------");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performDeposit(scanner);
                    break;
                case 3:
                    performWithdrawal(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting System. Good luck!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double initialDeposit = scanner.nextDouble();

        int accNumber = accountNumberCounter++;
        Account newAccount = new Account(accNumber, name, initialDeposit);
        accountMap.put(accNumber, newAccount);
        System.out.println("Account created successfully! Allocated Account Number: " + accNumber);
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int accNum = scanner.nextInt();
        Account acc = accountMap.get(accNum);

        if (acc != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account number not found!");
        }
    }

    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int accNum = scanner.nextInt();
        Account acc = accountMap.get(accNum);

        if (acc != null) {
            System.out.print("Enter Withdrawal Amount: ");
            double amount = scanner.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account number not found!");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        int accNum = scanner.nextInt();
        Account acc = accountMap.get(accNum);

        if (acc != null) {
            System.out.printf("Account Holder: %s | Balance: $%.2f%n", 
                              acc.getAccountHolder(), acc.getBalance());
        } else {
            System.out.println("Account number not found!");
        }
    }
}