package test.java;// Now let's create comprehensive JUnit tests using AAA pattern with setup and teardown
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import test.java.BankAccount;

import static org.junit.Assert.*;

public class BankAccountTest {

    // Test fixtures - objects that will be used across multiple tests
    private BankAccount account;
    private BankAccount premiumAccount;
    private static int testCounter;

    // Class-level setup - runs once before all tests
    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting BankAccount Test Suite ===");
        testCounter = 0;
    }

    // Method-level setup - runs before each test method
    @Before
    public void setUp() {
        System.out.println("Setting up test #" + (++testCounter));

        // Arrange - Create fresh test fixtures for each test
        account = new BankAccount("ACC001", 1000.0);
        premiumAccount = new BankAccount("PREM001", 5000.0);

        // Any other setup logic can go here
        System.out.println("Test fixtures created successfully");
    }

    // Method-level teardown - runs after each test method
    @After
    public void tearDown() {
        System.out.println("Cleaning up after test #" + testCounter);

        // Clean up resources, reset state, etc.
        account = null;
        premiumAccount = null;

        System.out.println("Test cleanup completed\n");
    }

    // Class-level teardown - runs once after all tests
    @AfterClass
    public static void tearDownClass() {
        System.out.println("=== Completed BankAccount Test Suite ===");
        System.out.println("Total tests executed: " + testCounter);
    }

    // Test 1: Basic deposit functionality using AAA pattern
    @Test
    public void testDepositValidAmount() {
        // Arrange
        double initialBalance = account.getBalance();
        double depositAmount = 500.0;
        double expectedBalance = initialBalance + depositAmount;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals("Balance should be updated after deposit",
                expectedBalance, account.getBalance(), 0.01);
        assertTrue("Account should remain active after deposit",
                account.isActive());
    }

    // Test 2: Withdrawal functionality using AAA pattern
    @Test
    public void testWithdrawValidAmount() {
        // Arrange
        double initialBalance = account.getBalance();
        double withdrawAmount = 300.0;
        double expectedBalance = initialBalance - withdrawAmount;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals("Balance should be reduced after withdrawal",
                expectedBalance, account.getBalance(), 0.01);
        assertTrue("Account should remain active after withdrawal",
                account.isActive());
    }

    // Test 3: Exception handling using AAA pattern
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double withdrawAmount = 1500.0; // More than account balance

        // Act
        account.withdraw(withdrawAmount);

        // Assert - Exception should be thrown (handled by expected annotation)
    }

    // Test 4: Multiple operations using AAA pattern
    @Test
    public void testMultipleTransactions() {
        // Arrange
        double deposit1 = 200.0;
        double deposit2 = 150.0;
        double withdrawal = 100.0;
        double expectedBalance = 1000.0 + deposit1 + deposit2 - withdrawal;

        // Act
        account.deposit(deposit1);
        account.deposit(deposit2);
        account.withdraw(withdrawal);

        // Assert
        assertEquals("Balance should reflect all transactions",
                expectedBalance, account.getBalance(), 0.01);
    }

    // Test 5: Account closure using AAA pattern
    @Test
    public void testAccountClosure() {
        // Arrange
        assertTrue("Account should be active initially", account.isActive());

        // Act
        account.closeAccount();

        // Assert
        assertFalse("Account should be inactive after closure",
                account.isActive());
    }

    // Test 6: Operations on closed account using AAA pattern
    @Test(expected = IllegalStateException.class)
    public void testDepositOnClosedAccount() {
        // Arrange
        account.closeAccount();
        double depositAmount = 100.0;

        // Act
        account.deposit(depositAmount);

        // Assert - Exception should be thrown (handled by expected annotation)
    }

    // Test 7: Boundary value testing using AAA pattern
    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        // Arrange
        double negativeAmount = -50.0;

        // Act
        account.deposit(negativeAmount);

        // Assert - Exception should be thrown
    }

    // Test 8: Using premium account fixture
    @Test
    public void testPremiumAccountOperations() {
        // Arrange
        double largeWithdrawal = 2000.0;
        double expectedBalance = 5000.0 - largeWithdrawal;

        // Act
        premiumAccount.withdraw(largeWithdrawal);

        // Assert
        assertEquals("Premium account should handle large withdrawals",
                expectedBalance, premiumAccount.getBalance(), 0.01);
        assertEquals("Account number should be correct",
                "PREM001", premiumAccount.getAccountNumber());
    }

    // Test 9: Testing with custom assertion messages
    @Test
    public void testAccountCreation() {
        // Arrange
        String expectedAccountNumber = "ACC001";
        double expectedBalance = 1000.0;

        // Act
        // (account already created in setUp method)

        // Assert
        assertNotNull("Account should not be null", account);
        assertEquals("Account number should match",
                expectedAccountNumber, account.getAccountNumber());
        assertEquals("Initial balance should be correct",
                expectedBalance, account.getBalance(), 0.01);
        assertTrue("New account should be active", account.isActive());
    }

    // Test 10: Testing edge case - zero balance after withdrawal
    @Test
    public void testWithdrawToZeroBalance() {
        // Arrange
        double totalBalance = account.getBalance();

        // Act
        account.withdraw(totalBalance);

        // Assert
        assertEquals("Balance should be zero after withdrawing total amount",
                0.0, account.getBalance(), 0.01);
        assertTrue("Account should still be active with zero balance",
                account.isActive());
    }
}

// Alternative example using JUnit 5 annotations for comparison
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTestJUnit5 {
    
    private BankAccount account;
    
    @BeforeAll
    static void setUpClass() {
        System.out.println("=== Starting BankAccount Test Suite (JUnit 5) ===");
    }
    
    @BeforeEach
    void setUp() {
        // Arrange - Create fresh test fixtures
        account = new BankAccount("ACC001", 1000.0);
    }
    
    @AfterEach
    void tearDown() {
        // Clean up
        account = null;
    }
    
    @AfterAll
    static void tearDownClass() {
        System.out.println("=== Completed BankAccount Test Suite (JUnit 5) ===");
    }
    
    @Test
    void testDepositValidAmount() {
        // Arrange
        double depositAmount = 500.0;
        double expectedBalance = 1500.0;
        
        // Act
        account.deposit(depositAmount);
        
        // Assert
        assertEquals(expectedBalance, account.getBalance());
    }
}
*/