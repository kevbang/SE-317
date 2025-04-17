package code;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        boolean exit = false; // Flag to control the main loop
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Create a new user with initial checking and savings account balances
        User user = new User("John Doe", "password123", 1000, 500);

        System.out.println("Welcome to the ATM, " + user.getName() + "!");

        // Main loop for ATM operations
        while (!exit) {
            try {
                // Display menu options
                System.out.println("\nPlease select an option:");
                System.out.println("1. Check Checking Account Balance");
                System.out.println("2. Check Savings Account Balance");
                System.out.println("3. Deposit to Checking Account");
                System.out.println("4. Deposit to Savings Account");
                System.out.println("5. Withdraw from Checking Account");
                System.out.println("6. Transfer from Checking to Savings");
                System.out.println("7. Transfer from Savings to Checking");
                System.out.println("8. Pay a bill");
                System.out.println("9. View Utility Account Bill Payment History");
                System.out.println("10. Check Next Utility Bill");
                System.out.println("11. Create a New Utility Account");
                System.out.println("12. Exit");

                // Get user choice
                int choice = scanner.nextInt();

                // Handle user choice
                switch (choice) {
                    case 1:
                        // Display checking account balance
                        System.out.println("Checking Account Balance: $" + user.getCheckingAccount().getBalance());
                        break;
                    case 2:
                        // Display savings account balance
                        System.out.println("Savings Account Balance: $" + user.getSavingsAccount().getBalance());
                        break;
                    case 3:
                        // Deposit to checking account
                        System.out.print("Enter amount to deposit to Checking Account: ");
                        double depositChecking = scanner.nextDouble();
                        if (user.performDailyDeposit(depositChecking)) {
                            System.out.println("Deposit successful!");
                        }
                        break;
                    case 4:
                        // Deposit to savings account
                        System.out.print("Enter amount to deposit to Savings Account: ");
                        double depositSavings = scanner.nextDouble();
                        if (depositSavings <= 0) {
                            System.out.println("Invalid deposit amount. Must be positive.");
                        } else if (user.getSavingsAccount().deposit(depositSavings)) {
                            System.out.println("Deposit to Savings Account successful!");
                        } else {
                            System.out.println("Deposit failed. Daily deposit limit exceeded.");
                        }
                        break;

                    case 5:
                        // Withdraw from checking account
                        System.out.print("Enter amount to withdraw from Checking Account: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount <= 0) {
                            System.out.println("Invalid withdrawal amount. Must be positive.");
                        } else if (user.getCheckingAccount().withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Withdrawal failed. Insufficient funds or daily limit reached.");
                        }
                        break;

                    case 6:
                        // Transfer from checking to savings
                        System.out.print("Enter amount to transfer from Checking to Savings: ");
                        double transferToSavings = scanner.nextDouble();
                        if (transferToSavings <= 0) {
                            System.out.println("Invalid transfer amount. Must be positive.");
                        } else if (user.getCheckingAccount().transferToSavings(user.getSavingsAccount(), transferToSavings)) {
                            System.out.println("Transfer to Savings Account successful!");
                        } else {
                            System.out.println("Transfer failed. Insufficient funds or daily limit reached.");
                        }
                        break;

                    case 7:
                        // Transfer from savings to checking
                        System.out.print("Enter amount to transfer from Savings to Checking: ");
                        double transferToChecking = scanner.nextDouble();
                        if (transferToChecking <= 0) {
                            System.out.println("Invalid transfer amount. Must be positive.");
                        } else if (user.getSavingsAccount().transferToChecking(user.getCheckingAccount(), transferToChecking)) {
                            System.out.println("Transfer to Checking Account successful!");
                        } else {
                            System.out.println("Transfer failed. Daily transfer limit exceeded or cannot transfer more than $100.");
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
                        // Display the last 3 bill payments
                        System.out.println("Last 3 Bill Payments:");
                        for (String bill : user.getUtilityAccount().getBillPaymentHistory()) {
                            System.out.println(bill);
                        }
                        break;
                    case 10:
                        // Display the next utility bill amount and due date
                        System.out.println("Next Bill Amount: $" + user.getUtilityAccount().getNextBillAmount());
                        System.out.println("Due Date: " + user.getUtilityAccount().getNextBillAmount());
                        break;
                    case 11:
                        // Create a new utility account
                        scanner.nextLine(); // Consume the newline left by nextInt()
                        System.out.print("Enter a username for the new utility account: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter a password for the new utility account: ");
                        String password = scanner.nextLine();
                        UtilityAccount newUtilityAccount = new UtilityAccount(username, password);
                        user.setUtilityAccount(newUtilityAccount);
                        System.out.println("Utility account created successfully!");
                        System.out.println("Your new utility account number is: " + newUtilityAccount.getAccountNumber());
                        break;
                    case 12:
                        // Exit the ATM
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        exit = true;
                        break;
                    default:
                        // Handle invalid menu option
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close(); // Close the scanner
    }
}