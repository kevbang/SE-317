package code;

import java.time.LocalDate;

public class User {

    private String name;
    private String password;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;
    private UtilityAccount utilityAccount;
    private LocalDate lastResetDate;
    private double dailyDepositTotal;


    public User(String name, String password, double initialChecking, double initialSavings) {
        if (initialChecking < 0 || initialSavings < 0) {
            throw new IllegalArgumentException("Initial balances cannot be negative.");
        }

        this.name = name;
        this.password = password;
        this.checkingAccount = new CheckingAccount(initialChecking);
        this.savingsAccount = new SavingsAccount(initialSavings);
        this.utilityAccount = new UtilityAccount(name, password);
        this.lastResetDate = LocalDate.now();
        this.dailyDepositTotal = 0.0;
    }

    /**
     * Resets daily limits if the date has changed.
     */
    private void resetDailyLimits() {
        LocalDate today = LocalDate.now();
        if (!today.equals(lastResetDate)) {
            checkingAccount.resetDailyLimits();
            dailyDepositTotal = 0.0;
            lastResetDate = today;
        }
    }

    /**
     * Sets the checking account for the user.
     * @param checkingAccount The checking account to set.
     */
    public void setUtilityAccount(UtilityAccount utilityAccount) {
        this.utilityAccount = utilityAccount;
    }

    /**
     * Performs a daily deposit action for the user.
     * @param depositAmount The amount to deposit.
     * @return true if the deposit is successful, false otherwise.
     */
    public boolean performDailyDeposit(double depositAmount) {
        if (depositAmount <= 0) {
            System.out.println("** The deposit has failed. Deposit amount must be positive. **");
            return false;
        }
        resetDailyLimits();

        if (dailyDepositTotal + depositAmount > 5000) {
            System.out.println("** The deposit has failed. Daily deposit limit of $5000 has been exceeded. **");
            return false;
        }

        if (checkingAccount.deposit(depositAmount)) {
            dailyDepositTotal += depositAmount;
            System.out.println("** The deposit has been successful. **");
            return true;
        } else {
            System.out.println("** The deposit has failed. **");
            return false;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public CheckingAccount getCheckingAccount() {
        resetDailyLimits();
        return checkingAccount;
    }

    public SavingsAccount getSavingsAccount() {
        resetDailyLimits();
        return savingsAccount;
    }

    public UtilityAccount getUtilityAccount() {
        return utilityAccount;
    }

    public LocalDate getLastResetDate() {
        return lastResetDate;
    }

    // Testing
    public static void main(String[] args) {
        User user1 = new User("John Deere", "Deere", 1000, 500);

        System.out.println("User: " + user1.getName());

        user1.getCheckingAccount().transferToSavings(user1.getSavingsAccount(), 500);
        System.out.println("Checking Account Balance: " + user1.getCheckingAccount().getBalance());
        System.out.println("Savings Account Balance: " + user1.getSavingsAccount().getBalance());

        user1.performDailyDeposit(500);
        user1.performDailyDeposit(4501);
        System.out.println("Checking: " + user1.getCheckingAccount().getBalance() + " Savings: " + user1.getSavingsAccount().getBalance());
    }
}