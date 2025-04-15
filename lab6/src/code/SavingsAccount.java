package code;

/**
 * SavingsAccount class that extends the Account class.
 * This class represents a savings account with basic deposit and withdrawal functionality.
 * @author Kevin Tran Anthony Phan
 */
public class SavingsAccount extends Account {

    // Constants for daily limits
    private static final double DAILY_DEPOSIT_LIMIT = 5000.0;
    private static final double DAILY_TRANSFER_LIMIT = 100.0;

    // Variables to track daily deposit and transfer totals
    private double dailyDepositTotal = 0.0;
    private double dailyTransferTotal = 0.0;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    /**
     * Deposits a specified amount into the savings account.
     * Ensures the daily deposit limit is not exceeded.
     * @param amount The amount to deposit.
     * @return true if the deposit is successful, false otherwise.
     */
    @Override
    public boolean deposit(double amount) {
        if (amount > 0 && (dailyDepositTotal + amount) <= DAILY_DEPOSIT_LIMIT) {
            this.balance += amount;
            dailyDepositTotal += amount;
            return true;
        }
        return false;
    }

    /**
     * Withdrawals are not allowed from the savings account.
     * @param amount The amount to withdraw.
     * @return false always, as withdrawals are not permitted.
     */
    @Override
    public boolean withdraw(double amount) {
        return false; // Withdrawals are not allowed
    }

    /**
     * Bill payments are not allowed from the savings account.
     * @param utilityAccount The utility account to pay.
     * @param amount The amount to pay.
     * @throws UnsupportedOperationException always, as bill payments are not permitted.
     */
    public boolean payBill(UtilityAccount utilityAccount, double amount) {
        throw new UnsupportedOperationException("Bill payments are not allowed from a savings account.");
    }

    /**
     * Transfers a specified amount from this savings account to a checking account.
     * Ensures the daily transfer limit is not exceeded and prevents overdraft.
     * @param checkingAccount The checking account to transfer to.
     * @param amount The amount to transfer.
     * @return true if the transfer is successful, false otherwise.
     */
    public boolean transferToChecking(CheckingAccount checkingAccount, double amount) {
        if (amount > 0 && (dailyTransferTotal + amount) <= DAILY_TRANSFER_LIMIT && amount <= this.balance) {
            this.balance -= amount;
            dailyTransferTotal += amount;
            checkingAccount.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the current balance of the savings account.
     * @return The current balance.
     */
    @Override
    public double getBalance() {
        return this.balance;
    }

    /**
     * Resets the daily deposit and transfer totals.
     * This method should be called at the end of each day.
     */
    public void resetDailyLimits() {
        dailyDepositTotal = 0.0;
        dailyTransferTotal = 0.0;
    }
}