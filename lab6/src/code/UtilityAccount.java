package code;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class UtilityAccount {
    private String username;
    private String password;
    private int accountNumber;
    private List<String> billPaymentHistory;
    private double nextBillAmount;
    private String nextBillDueDate;

    /*
    Generates a random account number for the user
    @return a random 6-digit account number
     */
    private int randomAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000); // Ensures a 6-digit number
    }

    public UtilityAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNumber = randomAccountNumber();
        this.billPaymentHistory = new ArrayList<>();
    }

    // Logs in using username or account number and password
    public boolean login(String identifier, String password) {
        return (identifier.equals(this.username) || identifier.equals(String.valueOf(this.accountNumber)))
                && this.password.equals(password);
    }

    // Adds a bill to the payment history (keeps only the last 3 bills)
    public void addBillPayment(String billDetails) {
        if (billPaymentHistory.size() == 3) {
            billPaymentHistory.remove(0); // Remove the oldest bill
        }
        billPaymentHistory.add(billDetails); // Add the new bill
    }

    // Retrieves the last 3 paid bills
    public List<String> getBillPaymentHistory() {
        return new ArrayList<>(billPaymentHistory);
    }

    // Sets the next bill payment amount and due date
    public void setNextBill(double amount, String dueDate) {
        if (amount < 0) {
            throw new IllegalArgumentException("Bill amount cannot be negative.");
        }
        this.nextBillAmount = amount;
        this.nextBillDueDate = dueDate;
    }

    // Retrieves the next bill payment amount
    public double getNextBillAmount() {
        return nextBillAmount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
