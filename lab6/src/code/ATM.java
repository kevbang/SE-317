package code;

import java.util.Scanner;

/**
 * ATM class that simulates an ATM machine.
 * It allows users to check balances, deposit, withdraw, transfer money, and pay bills.
 */
public class ATM {
    public static void main(String[] args) {
        boolean exit = false; // Flag to control the ATM loop
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Create a new user with initial checking and savings account balances
        User user = new User("John Doe", "password123", 1000, 500);

        System.out.println("Welcome to the ATM, " + user.getName() + "!");

        // Main loop for the ATM menu
        while (!exit) {
            try {
                // Display the menu options
                System.out.println("\nPlease select an option:");
                System.out.println("1. Check Checking Account Balance");
                System.out.println("2. Check Savings Account Balance");
                System.out.println("3. Deposit to Checking Account");
                System.out.println("4. Deposit to Savings Account");
                System.out.println("5. Withdraw from Checking Account");
                System.out.println("6. Transfer from Checking to Savings");
                System.out.println("7. Transfer from Savings to Checking");
                System.out.println("8. Pay a bill");
                System.out.println("9. Exit");

                // Get the user's choice
                int choice = scanner.nextInt();

                // Handle the user's choice
                switch (choice) {
                    case 1:
                        // Display the checking account balance
                        System.out.println("Checking Account Balance: $" + user.getCheckingAccount().getBalance());
                        break;
                    case 2:
                        // Display the savings account balance
                        System.out.println("Savings Account Balance: $" + user.getSavingsAccount().getBalance());
                        break;
                    case 3:
                        // Deposit to the checking account
                        System.out.print("Enter amount to deposit to Checking Account: ");
                        double depositChecking = scanner.nextDouble();
                        if (user.performDailyDeposit(depositChecking)) {
                            System.out.println("Deposit successful!");
                        }
                        break;
                    case 8:
                        // Pay a bill using the checking account
                        System.out.print("Enter bill amount to pay: ");
                        double billAmount = scanner.nextDouble();
                        if (billAmount <= 0) {
                            System.out.println("Invalid bill amount. Must be positive.");
                            break;
                        }
                        UtilityAccount utilityAccount = user.getUtilityAccount();
                        if (user.getCheckingAccount().payBill(utilityAccount, billAmount)) {
                            utilityAccount.addBillPayment("Paid $" + billAmount);
                            System.out.println("Bill payment successful!");
                        } else {
                            System.out.println("Bill payment failed. Check your balance.");
                        }
                        break;
                    case 9:
                        // Exit the ATM
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        exit = true;
                        break;
                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                // Handle invalid input (e.g., non-numeric input)
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close(); // Close the scanner
    }
}