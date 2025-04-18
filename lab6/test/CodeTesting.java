import code.CheckingAccount;
import code.SavingsAccount;
import code.UtilityAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CodeTesting {

    // CheckingAccount Tests

    // Test a valid deposit to the checking account and verify the balance is updated correctly
    @Test
    public void testValidDepositCheckingAccount() {
        CheckingAccount account = new CheckingAccount(1000);
        assertTrue(account.deposit(500)); // Valid deposit
        assertEquals(1500, account.getBalance());
    }

    // Test an invalid deposit that exceeds the daily limit and verify the balance remains unchanged
    @Test
    public void testInvalidDepositExceedingLimitCheckingAccount() {
        CheckingAccount account = new CheckingAccount(1000);
        assertFalse(account.deposit(6000)); // Exceeds daily limit
        assertEquals(1000, account.getBalance());
    }

    // Test a valid withdrawal from the checking account and verify the balance is updated correctly
    @Test
    public void testValidWithdrawalCheckingAccount() {
        CheckingAccount account = new CheckingAccount(1000);
        assertTrue(account.withdraw(200)); // Valid withdrawal
        assertEquals(800, account.getBalance());
    }

    // Test an invalid withdrawal that exceeds the account balance and verify the balance remains unchanged
    @Test
    public void testInvalidWithdrawalExceedingBalanceCheckingAccount() {
        CheckingAccount account = new CheckingAccount(1000);
        assertFalse(account.withdraw(1200)); // Exceeds balance
        assertEquals(1000, account.getBalance());
    }

    // Test the daily withdrawal limit by attempting to withdraw more than the allowed amount in a day
    @Test
    public void testDailyWithdrawalLimitCheckingAccount() {
        CheckingAccount account = new CheckingAccount(1000);
        account.withdraw(500); // First withdrawal
        assertFalse(account.withdraw(600)); // Exceeds daily limit
        assertEquals(500, account.getBalance());
    }

    // SavingsAccount Tests

    // Test a valid deposit to the savings account and verify the balance is updated correctly
    @Test
    public void testValidDepositSavingsAccount() {
        SavingsAccount account = new SavingsAccount(500);
        assertTrue(account.deposit(300)); // Valid deposit
        assertEquals(800, account.getBalance());
    }

    // Test an invalid deposit with a negative amount and verify the balance remains unchanged
    @Test
    public void testInvalidDepositNegativeAmountSavingsAccount() {
        SavingsAccount account = new SavingsAccount(500);
        assertFalse(account.deposit(-100)); // Negative deposit
        assertEquals(500, account.getBalance());
    }

    // Test a valid transfer from the checking account to the savings account and verify both balances are updated correctly
    @Test
    public void testValidTransferFromCheckingToSavings() {
        CheckingAccount checking = new CheckingAccount(1000);
        SavingsAccount savings = new SavingsAccount(500);
        assertTrue(checking.transferToSavings(savings, 300)); // Valid transfer
        assertEquals(700, checking.getBalance());
        assertEquals(800, savings.getBalance());
    }

    // Test an invalid transfer from the checking account to the savings account that exceeds the checking account balance
    @Test
    public void testInvalidTransferExceedingBalanceFromCheckingToSavings() {
        CheckingAccount checking = new CheckingAccount(1000);
        SavingsAccount savings = new SavingsAccount(500);
        assertFalse(checking.transferToSavings(savings, 1200)); // Exceeds balance
        assertEquals(1000, checking.getBalance());
        assertEquals(500, savings.getBalance());
    }
}