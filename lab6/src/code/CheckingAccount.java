package code;

/**
 * CheckingAccount class that extends the Account class.
 * @author Kevin Tran Anthony Phan
 */
public class CheckingAccount extends Account{

    public CheckingAccount(double initialBalance) {
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

    public boolean transferToSavings(SavingsAccount savingsAccount, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            savingsAccount.deposit(amount);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        CheckingAccount acc = new CheckingAccount(999);

        System.out.println(acc.getBalance());
    }
}
