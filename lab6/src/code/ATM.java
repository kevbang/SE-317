package code;

import java.util.Scanner;

/**
 * ATM class that simulates an ATM machine.
 * It allows users to check balances, deposit, withdraw, transfer money, and pay bills.
 */
public class ATM {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sncr = new Scanner(System.in);

        // Create a scanner for user input
        User user = new User("John Doe", "password123", 1000, 500);

        System.out.println("Welcome to the ATM, " + user.getName() + "!");

        while(!exit) {
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

            int choice = scnr.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Checking Account Balance: $" + user.checkingAccount.getBalance());
                    break;
                case 2:
                    System.out.println("Savings Account Balance: $" + user.savingsAccount.getBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to deposit to Checking Account: ");
                    double depositChecking = scanner.nextDouble();
                    if (user.checkingAccount.deposit(depositChecking)) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Deposit failed. Check the daily limit or amount.");
                    }
                    break;
                case 4:
                    System.out.print("Enter amount to deposit to Savings Account: ");
                    double depositSavings = scanner.nextDouble();
                    if (user.savingsAccount.deposit(depositSavings)) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Deposit failed. Check the daily limit or amount.");
                    }
                    break;
                case 5:
                    System.out.print("Enter amount to withdraw from Checking Account: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (user.checkingAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Withdrawal failed. Check the daily limit or balance.");
                    }
                    break;
                case 6:
                    System.out.print("Enter amount to transfer from Checking to Savings: ");
                    double transferToSavings = scanner.nextDouble();
                    if (user.checkingAccount.transferToSavings(user.savingsAccount, transferToSavings)) {
                        System.out.println("Transfer successful!");
                    } else {
                        System.out.println("Transfer failed. Check the balance or amount.");
                    }
                    break;
                case 7:
                    System.out.print("Enter amount to transfer from Savings to Checking: ");
                    double transferToChecking = scanner.nextDouble();
                    if (user.savingsAccount.transferToChecking(user.checkingAccount, transferToChecking)) {
                        System.out.println("Transfer successful!");
                    } else {
                        System.out.println("Transfer failed. Check the daily limit or balance.");
                    }
                    break;
                case 8:
                    System.out.print("Enter bill amount to pay: ");
                    double billAmount = scanner.nextDouble();
                    UtilityAccount utilityAccount = user.getUtilityAccount();
                    if (user.getCheckingAccount().payBill(utilityAccount, billAmount)) {
                        utilityAccount.addBillPayment("Paid $" + billAmount);
                        System.out.println("Bill payment successful!");
                    } else {
                        System.out.println("Bill payment failed. Check your balance.");
                    }
                    break;
                case 9:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
        }
    }
        scanner.close();
    }
}