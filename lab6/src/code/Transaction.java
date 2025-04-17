package code;

import java.time.LocalDateTime;

/**
 * Transaction class to log details of deposits, withdrawals, transfers, and bill payments.
 */
public class Transaction {
    private String type; // e.g., "Deposit", "Withdrawal", "Transfer", "Bill Payment"
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}