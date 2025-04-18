package code;

import java.util.*;
import java.text.SimpleDateFormat;

public class UtilityAccount {
    private String username;
    private String password;
    private int accountNumber;
    private double nextBillAmount = 50; // starts at 50.
    public String nextBillDueDate;
    public List<String> billPaymentHistory;

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
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd");
        nextBillDueDate = formatter.format(date);

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

    // Retrieves the next bill due date
    public void setNextBillDueDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd");
            Date currentDate = formatter.parse(nextBillDueDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1); // Add one month
                nextBillDueDate = formatter.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculateBillPayment(double amount) {
        try {
            if (amount > 0 && amount <= nextBillAmount) {
                nextBillAmount -= amount;
                if(nextBillAmount == 0) {
                    nextBillAmount = 50; // Reset to 50 after full payment
                    this.setNextBillDueDate();

                }
            }
        }
        catch (Exception e) {
            System.out.println("** Something has gone wrong with your bill payment. **");
        }

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
