package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

@DisplayName("JUnit 5 Assertions Test")
public class AssertionsTest {

    @Test
    @DisplayName("Basic Assertions Test")
    public void testBasicAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3, "Addition should return 5");
        assertEquals("Hello", "Hello", "Strings should be equal");

        // Assert true/false
        assertTrue(5 > 3, "5 should be greater than 3");
        assertFalse(5 < 3, "5 should not be less than 3");

        // Assert null/not null
        assertNull(null, "Should be null");
        assertNotNull(new Object(), "Should not be null");
    }

    @Test
    @DisplayName("String Assertions")
    public void testStringAssertions() {
        String greeting = "Hello World";

        assertEquals("Hello World", greeting);
        assertTrue(greeting.startsWith("Hello"));
        assertTrue(greeting.endsWith("World"));
        assertTrue(greeting.contains("lo Wo"));
        assertEquals(11, greeting.length());
        assertFalse(greeting.isEmpty());
    }

    @Test
    @DisplayName("Array and Collection Assertions")
    public void testArrayAssertions() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = {1, 2, 3, 4, 5};

        assertArrayEquals(expected, actual, "Arrays should be equal");

        // Test array length
        assertEquals(5, actual.length, "Array length should be 5");

        // Test individual elements
        assertEquals(1, actual[0], "First element should be 1");
        assertEquals(5, actual[4], "Last element should be 5");
    }

    @Test
    @DisplayName("Exception Assertions")
    public void testExceptionAssertions() {
        // Test that exception is thrown
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        }, "Should throw ArithmeticException for division by zero");

        assertEquals("/ by zero", exception.getMessage());

        // Test that no exception is thrown
        assertDoesNotThrow(() -> {
            int result = 10 / 2;
        }, "Should not throw exception for valid division");
    }

    @Test
    @DisplayName("Timeout Assertions")
    public void testTimeoutAssertions() {
        // Test that operation completes within timeout
        assertTimeout(Duration.ofSeconds(1), () -> {
            Thread.sleep(100); // Simulate work
            return "Completed";
        }, "Operation should complete within 1 second");
    }

    @Test
    @DisplayName("Grouped Assertions")
    public void testGroupedAssertions() {
        User user = new User("John Doe", 25, "john@example.com");

        assertAll("User properties",
                () -> assertEquals("John Doe", user.getName()),
                () -> assertEquals(25, user.getAge()),
                () -> assertEquals("john@example.com", user.getEmail()),
                () -> assertTrue(user.isAdult()),
                () -> assertTrue(user.isValidUser())
        );
    }
}