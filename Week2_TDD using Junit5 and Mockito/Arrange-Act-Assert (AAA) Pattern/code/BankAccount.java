package test.java;

// First, let's create a simple class to test
class BankAccount {
    private String accountNumber;
    private double balance;
    private boolean isActive;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.isActive = true;
    }

    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void closeAccount() {
        isActive = false;
    }
}