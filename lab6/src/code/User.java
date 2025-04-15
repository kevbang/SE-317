package code;

import java.time.LocalDate;
public class User {

    private String name;
    private String password;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;
    // used to track the current date
    private LocalDate date;
    private double dailyDepositTotal = 0;


    public User(String name, String password, double initialChecking, double initialSavings) {
        this.name = name;
        this.password = password;
        this.date = LocalDate.now();

        try {
            if (initialChecking < 0) {
                throw new IllegalArgumentException("Initial checking account balance cannot be negative.");
            }
            if (initialSavings < 0) {
                throw new IllegalArgumentException("Initial savings account balance cannot be negative.");
            }

            // if positive amount, then create the accounts
            this.checkingAccount = new CheckingAccount(initialChecking);
            this.savingsAccount = new SavingsAccount(initialSavings);

        } catch (IllegalArgumentException e) {
            System.out.println("Error creating user for " + this.name + ": "+ e.getMessage());
        }


    }

//    /*
//     * This method is used to perform a daily action for the user.
//     * @param action The action we want to choose.
//     */
//    public boolean performDailyAction(User name, double depositAmount) {
//        LocalDate today = LocalDate.now();
//        System.out.println("Today is " + today);
//        // Check if the date has changed
//        if (today.isAfter(name.date)) {
//            // Update the date to today
//            name.date = today;
//            this.dailyDepositTotal = 0; // Reset the daily deposit total
//        }
//
//        if(dailyDepositTotal + depositAmount > 5000) {
//            System.out.println("** The deposit has failed. Daily deposit limit of $5000 has been exceeded. **");
//            return false;
//        }
//
//        if(name.checkingAccount.deposit(depositAmount)) {
//            this.dailyDepositTotal += depositAmount;
//            System.out.println("** The deposit has been successful. **");
//            System.out.println("Deposit Total: " + dailyDepositTotal);
//        } else {
//            System.out.println("** The deposit has failed. **");
//            return false;
//        }
//
//        return true;
//    }




    public static void main(String[] args) {
        User user1 = new User("John Deere", "Deere", 1000, 500);

        System.out.println("User: " + user1.name + " " + user1.password);

        user1.checkingAccount.transferToSavings(user1.savingsAccount, 500);
        System.out.println("Checking Account Balance: " + user1.checkingAccount.getBalance());
        System.out.println("Savings Account Balance: " + user1.savingsAccount.getBalance());

        LocalDate date = user1.date;

        user1.performDailyAction(user1, 500);
        user1.performDailyAction(user1, 4501);
        System.out.println("Checking: " + user1.checkingAccount.getBalance() + " Savings: " + user1.savingsAccount.getBalance());


    }

}
