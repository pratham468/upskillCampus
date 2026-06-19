import java.util.*;

public class Account {
    static int accCounter = 1001;
    int accountNumber;
    double balance;
    List<String> transactions;

    public Account(double initialDeposit) {
        this.accountNumber = accCounter++;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount + " | Balance: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        transactions.add("Withdrawn: " + amount + " | Balance: " + balance);
        return true;
    }
}
