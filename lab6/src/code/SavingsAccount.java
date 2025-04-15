package code;

/**
 * SavingsAccount class that extends the Account class.
 * This class represents a savings account with basic deposit and withdrawal functionality.
 * @author Kevin Tran Anthony Phan
 */
public class SavingsAccount extends Account{

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferToChecking(CheckingAccount checkingAccount, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            checkingAccount.deposit(amount);
            return true;
        }
        return false;
    }

}
