package test;

import code.UtilityAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilityAccountTest {
    @Test
    public void testLogin() {
        // Create a UtilityAccount instance
        UtilityAccount account = new UtilityAccount("testUser", "testPass");
        int accountNumber = account.getAccountNumber();

        // TR1: Login succeeds with the correct username and password
        Assertions.assertTrue(account.login("testUser", "testPass"), "Login should succeed with correct username and password.");

        // TR2: Login succeeds with the correct account number and password
        Assertions.assertTrue(account.login(String.valueOf(accountNumber), "testPass"), "Login should succeed with correct account number and password.");

        // TR3: Login fails with an incorrect username and correct password
        Assertions.assertFalse(account.login("wrongUser", "testPass"), "Login should fail with incorrect username and correct password.");

        // TR4: Login fails with an incorrect account number and correct password
        Assertions.assertFalse(account.login("123456", "testPass"), "Login should fail with incorrect account number and correct password.");

        // TR5: Login fails with the correct username or account number but incorrect password
        Assertions.assertFalse(account.login("testUser", "wrongPass"), "Login should fail with correct username but incorrect password.");
        Assertions.assertFalse(account.login(String.valueOf(accountNumber), "wrongPass"), "Login should fail with correct account number but incorrect password.");
    }

    @Test
    public void testCalculateBillPayment() {
        // Create a UtilityAccount instance
        UtilityAccount account = new UtilityAccount("testUser", "testPass");

        // TR1: Payment is successfully deducted when the amount is valid
        account.calculateBillPayment(20);
        Assertions.assertEquals(30, account.getNextBillAmount(), "Next bill amount should be reduced by the payment amount.");

        // TR2: Full payment resets the next bill amount and updates the due date
        account.calculateBillPayment(30);
        Assertions.assertEquals(50, account.getNextBillAmount(), "Next bill amount should reset to 50 after full payment.");
        String initialDueDate = account.nextBillDueDate;
        account.calculateBillPayment(50);
        Assertions.assertNotEquals(initialDueDate, account.nextBillDueDate, "Next bill due date should update after full payment.");

        // TR3: Invalid payment amounts do not change the bill amount
        account.calculateBillPayment(-10); // Negative amount
        Assertions.assertEquals(50, account.getNextBillAmount(), "Next bill amount should remain unchanged for negative payment.");
        account.calculateBillPayment(60); // Exceeds next bill amount
        Assertions.assertEquals(50, account.getNextBillAmount(), "Next bill amount should remain unchanged for payment exceeding the bill amount.");
    }

}
