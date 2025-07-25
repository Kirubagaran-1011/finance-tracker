import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String type; // "Income" or "Expense"
    String category;
    double amount;
    String date;

    public Transaction(String type, String category, double amount, String date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return type + " | Category: " + category + " | Amount: " + amount + " | Date: " + date;
    }
}

public class FinanceTracker {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Finance Tracker Menu ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. Edit Transaction");
            System.out.println("3. Delete Transaction");
            System.out.println("4. View Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    editTransaction();
                    break;
                case 3:
                    deleteTransaction();
                    break;
                case 4:
                    viewSummary();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTransaction() {
        System.out.println("\n=== Add Transaction ===");
        System.out.print("Enter type (Income/Expense): ");
        String type = scanner.nextLine();

        System.out.print("Enter category (e.g., Salary, Rent, Groceries): ");
        String category = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        transactions.add(new Transaction(type, category, amount, date));
        System.out.println("Transaction added successfully!");
    }

    private static void editTransaction() {
        System.out.println("\n=== Edit Transaction ===");
        viewTransactions();

        System.out.print("Enter the transaction number to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < transactions.size()) {
            System.out.println("Editing Transaction: " + transactions.get(index));

            System.out.print("Enter new type (Income/Expense): ");
            String type = scanner.nextLine();

            System.out.print("Enter new category: ");
            String category = scanner.nextLine();

            System.out.print("Enter new amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            transactions.set(index, new Transaction(type, category, amount, date));
            System.out.println("Transaction updated successfully!");
        } else {
            System.out.println("Invalid transaction number.");
        }
    }

    private static void deleteTransaction() {
        System.out.println("\n=== Delete Transaction ===");
        viewTransactions();

        System.out.print("Enter the transaction number to delete: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
            System.out.println("Transaction deleted successfully!");
        } else {
            System.out.println("Invalid transaction number.");
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
            return;
        }

        System.out.println("\n=== Transactions ===");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    private static void viewSummary() {
        System.out.println("\n=== Financial Summary ===");
        double totalIncome = 0, totalExpense = 0;

        for (Transaction transaction : transactions) {
            if (transaction.type.equalsIgnoreCase("Income")) {
                totalIncome += transaction.amount;
            } else if (transaction.type.equalsIgnoreCase("Expense")) {
                totalExpense += transaction.amount;
            }
        }

        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpense);
        System.out.println("Net Savings: " + (totalIncome - totalExpense));
    }
}
