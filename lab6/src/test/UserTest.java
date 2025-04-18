package test;

import code.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class UserTest
{

    @Test
    public void testUserCreation() {
        // TR1: User is created successfully with valid inputs
        User user = new User("JohnDoe", "password123", 1000.0, 500.0);
        Assertions.assertEquals("JohnDoe", user.getName(), "User name should be initialized correctly.");
        Assertions.assertEquals("password123", user.getPassword(), "User password should be initialized correctly.");
        Assertions.assertEquals(1000.0, user.getCheckingAccount().getBalance(), "Checking account balance should be initialized correctly.");
        Assertions.assertEquals(500.0, user.getSavingsAccount().getBalance(), "Savings account balance should be initialized correctly.");

        // TR2: Exception is thrown for negative initial checking balance
        Exception exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("JohnDoe", "password123", -100.0, 500.0);
        });
        Assertions.assertEquals("Initial balances cannot be negative.", exception1.getMessage(), "Exception message should match for negative checking balance.");

        // TR3: Exception is thrown for negative initial savings balance
        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("JohnDoe", "password123", 1000.0, -500.0);
        });
        Assertions.assertEquals("Initial balances cannot be negative.", exception2.getMessage(), "Exception message should match for negative savings balance.");
    }

    @Test
    public void testUserBoundaries() {
        User user = new User("JohnDoe", "password123", 1000.0, 500.0);

        // TR1: Daily deposit limit of $5000 is enforced
        boolean result1 = user.performDailyDeposit(5000.0);
        Assertions.assertTrue(result1, "Deposit of $5000 should be successful.");
        boolean result2 = user.performDailyDeposit(1.0);
        Assertions.assertFalse(result2, "Deposit exceeding daily limit should fail.");

        // TR2: Deposits of exactly $5000 are allowed
        user = new User("JohnDoe", "password123", 1000.0, 500.0); // Reset user
        boolean result3 = user.performDailyDeposit(5000.0);
        Assertions.assertTrue(result3, "Deposit of exactly $5000 should be allowed.");

        // TR3: Deposits of $0 or negative amounts are rejected
        boolean result4 = user.performDailyDeposit(0.0);
        Assertions.assertFalse(result4, "Deposit of $0 should be rejected.");
        boolean result5 = user.performDailyDeposit(-100.0);
        Assertions.assertFalse(result5, "Deposit of a negative amount should be rejected.");
    }


}
