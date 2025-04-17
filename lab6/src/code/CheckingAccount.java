package code;

/**
 * CheckingAccount class that extends the Account class.
 * Implements rules for deposits, withdrawals, transfers, and bill payments
 * @author Kevin Tran Anthony Phan
 */
public class CheckingAccount extends Account {
    // Constatnts for daily limits
    private static final double DAILY_DEPOSIT_LIMIT = 5000.0;
    private static final double DAILY_WITHDRAWAL_LIMIT = 500.0;

    // Variables to track daily deposit and withdrawal totals
    private double dailyDepositTotal = 0.0;
    private double dailyWithdrawalTotal = 0.0;

    public CheckingAccount(double initialBalance) {
        super(initialBalance);
    }

    /**
     * Deposits a specified amount into the account.
     * Ensures the daily deposit limit is not exceeded.
     * @param amount The amount to deposit.
     * @return true if the deposit is successful, false if the deposit failed.
     */
    public boolean deposit(double amount) {
        if (amount > 0 && (dailyDepositTotal + amount) <= DAILY_DEPOSIT_LIMIT) {
            this.balance += amount;
            dailyDepositTotal += amount;
            return true;
        }
        return false;
    }

    /**
     * Withdraws a specified amount from the account.
     * Ensures the daily withdrawal limit is not exceeded and prevents overdraft.
     * @param amount The amount to withdraw.
     * @return true if the withdrawal is successful, false if the withdrawal failed.
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && (dailyWithdrawalTotal + amount) <= DAILY_WITHDRAWAL_LIMIT && amount <= this.balance) {
            this.balance -= amount;
            dailyWithdrawalTotal += amount;
            return true;
        }
        return false;
    }

    /**
     * Transfers a specified amount from this checking account to a savings account.
     * Ensures the transfer amount does not exceed the current balance.
     * @param savingsAccount The savings account to transfer to.
     * @param amount The amount to transfer.
     * @return true if the transfer is successful, false if it failed to transfer to Savings.
     */
    public boolean transferToSavings(SavingsAccount savingsAccount, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            savingsAccount.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * Pays a bill to a utility account from this checking account.
     * Ensures the payment amount does not exceed the current balance.
     * @param utilityAccount The utility account to pay.
     * @param amount The amount to pay.
     * @return true if the payment is successful, false otherwise.
     */
    public boolean payBill(UtilityAccount utilityAccount, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            utilityAccount.addBillPayment("Paid $" + amount);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the current balance of the checking account.
     * @return The current balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Resets the daily deposit and withdrawal totals.
     * This method should be called at the end of each day.
     */
    public void resetDailyLimits() {
        dailyDepositTotal = 0.0;
        dailyWithdrawalTotal = 0.0;
    }
}

