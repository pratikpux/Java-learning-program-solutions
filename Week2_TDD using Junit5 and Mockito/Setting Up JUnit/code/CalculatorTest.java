import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        // This method runs before each test
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        // This method runs after each test
        calculator = null;
    }

    @Test
    public void testAddition() {
        // Test the addition method
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubtraction() {
        // Test the subtraction method
        int result = calculator.subtract(5, 3);
        assertEquals(2, result);
    }

    @Test
    public void testMultiplication() {
        // Test the multiplication method
        int result = calculator.multiply(4, 3);
        assertEquals(12, result);
    }

    @Test
    public void testDivision() {
        // Test the division method
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZero() {
        // Test that division by zero throws an exception
        calculator.divide(10, 0);
    }
}