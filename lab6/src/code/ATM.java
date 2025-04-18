package code;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        boolean exit = false; // Flag to control the main loop
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        String username;
        String password;

        // Display welcome message
        System.out.println("*******************************************************************************************");
        System.out.println("Welcome to the ATM! Please register using a username and password (e.g., \"user123, pass123\"). \n");
        String[] credentials = scanner.nextLine().split(",");
        username = credentials[0].trim();
        password = credentials[1].trim();

        System.out.println(username + " " + password);
        // Create a new user with the provided credentials
        User user = new User(username, password, 0.0, 0.0);

        System.out.println("Welcome " + user.getName() + "! You have successfully created an account at AK Banking.");

        // Main loop for the ATM
        while (!exit) {
            try {
                // Display menu options
                System.out.println("\nThank you for choosing AK Banking. Please select an option:");
                System.out.println("1. Check Checking Account Balance");
                System.out.println("2. Check Savings Account Balance");
                System.out.println("3. Deposit to Checking Account");
                System.out.println("4. Deposit to Savings Account");
                System.out.println("5. Withdraw from Checking Account");
                System.out.println("6. Transfer from Checking to Savings");
                System.out.println("7. Transfer from Savings to Checking");
                System.out.println("8. Create a New Utility Account");
                System.out.println("9. Log into Utility Account");
                System.out.println("10. Exit");

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
                        // Create a new utility account
                        scanner.nextLine(); // Consume the newline left by nextInt()
                        System.out.print("Enter a username for the new utility account: ");
                        String userName = scanner.nextLine();
                        System.out.print("Enter a password for the new utility account: ");
                        String passWord = scanner.nextLine();
                        UtilityAccount newUtilityAccount = new UtilityAccount(userName, passWord);
                        user.setUtilityAccount(newUtilityAccount);

                        System.out.println("Utility account created successfully!");
                        System.out.println("Your new utility account number is: " + newUtilityAccount.getAccountNumber());
                        System.out.println("To log into your utility account, use your username/utility\naccount number and password you created.");
                        break;

                    case 9:
                        System.out.print("Enter your username or utility account number: ");
                        scanner.nextLine(); // Consume the newline left by nextInt()
                        String accountUsername = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String accountPassword = scanner.nextLine();

                        boolean leave = false;

                        boolean verified = user.getUtilityAccount().login(accountUsername, accountPassword);
                        System.out.println(verified);
                        if (verified) {
                            while(!leave) {
                                System.out.println("\nUtility Account Menu:");
                                System.out.println("1. View Bill Payment History");
                                System.out.println("2. Pay Bill");
                                System.out.println("3. View Next Bill Amount and Due Date");
                                System.out.println("4. Logout");
                                System.out.print("Select an option: ");
                                int utilityChoice = scanner.nextInt();

                                switch (utilityChoice) {
                                    case 1:
                                        // View bill payment history
                                        System.out.println("Bill Payment History: " + user.getUtilityAccount().getBillPaymentHistory());
                                        break;
                                    case 2:
                                        // Pay bill
                                        System.out.print("Enter the amount to pay: ");
                                        double billAmount = scanner.nextDouble();
                                        if (user.getCheckingAccount().payBill(user.getUtilityAccount(), billAmount)) {
                                            System.out.println("Bill payment successful!");
                                        } else {
                                            System.out.println("Bill payment failed. Insufficient funds.");
                                        }
                                        break;
                                    case 3:
                                        // View next bill amount and due date
                                        System.out.println("Next Bill Amount: $" + user.getUtilityAccount().getNextBillAmount());
                                        break;
                                    case 4:
                                        // Logout from utility account
                                        leave = true;
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }
                            }
                        }
                        else {
                            System.out.println("Invalid username or utility account number.");
                        }
                        break;
                    case 10:
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