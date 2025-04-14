package code;

/**
 * This is the abstract class for the Account class. It contains the common properties and methods for both Checking and Savings accounts.
 *
 * @author Kevin Tran Anthony Phan
 * @version 1.0
 */
abstract class Account {

    protected double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    // You can do getBalance for both checking and savings accounts
    public double getBalance() {
        return this.balance;
    }

    // You can do deposit and withdraw for both checking and savings accounts, but different implementations, so lets use abstract methods
    public abstract boolean deposit(double amount);

    public abstract boolean withdraw(double amount);


}
