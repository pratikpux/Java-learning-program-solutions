import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    @Mock
    private ExternalApi mockApi;
    private MyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MyService(mockApi);
    }

    @Test
    public void testVerifyBasicInteraction() {
        // Act
        service.fetchData();

        // Verify the interaction
        verify(mockApi).getData();
    }

    @Test
    public void testVerifyInteractionWithSpecificArguments() {
        // Act
        service.fetchDataWithParameter("testParam");

        // Verify the method was called with specific arguments
        verify(mockApi).getData("testParam");
    }

    @Test
    public void testVerifyMultipleInteractions() {
        // Act
        service.processInformation("test data");

        // Verify multiple interactions
        verify(mockApi).processData("test data", 5);
        verify(mockApi).validateData("test data");
    }

    @Test
    public void testVerifyInteractionTimes() {
        // Act
        service.fetchMultipleData();

        // Verify method was called exactly 2 times
        verify(mockApi, times(2)).getData(anyString());

        // Verify specific calls
        verify(mockApi).getData("first");
        verify(mockApi).getData("second");
    }

    @Test
    public void testVerifyNoInteractions() {
        // Don't call any methods on service

        // Verify no interactions occurred
        verifyNoInteractions(mockApi);
    }

    @Test
    public void testVerifyNoMoreInteractions() {
        // Act
        service.fetchData();

        // Verify expected interaction
        verify(mockApi).getData();

        // Verify no other interactions occurred
        verifyNoMoreInteractions(mockApi);
    }

    @Test
    public void testVerifyInteractionOrder() {
        // Act
        service.processInformation("ordered test");

        // Create InOrder verifier
        InOrder inOrder = inOrder(mockApi);

        // Verify interactions happened in specific order
        inOrder.verify(mockApi).processData("ordered test", 5);
        inOrder.verify(mockApi).validateData("ordered test");
    }

    @Test
    public void testVerifyWithArgumentMatchers() {
        // Act
        service.fetchDataWithParameter("anyValue");
        service.processInformation("some data");

        // Verify using argument matchers
        verify(mockApi).getData(anyString());
        verify(mockApi).processData(eq("some data"), eq(5));
        verify(mockApi).validateData(contains("data"));
    }

    @Test
    public void testVerifyNeverCalled() {
        // Act - only call fetchData, not processInformation
        service.fetchData();

        // Verify getData was called
        verify(mockApi).getData();

        // Verify processData was never called
        verify(mockApi, never()).processData(anyString(), anyInt());
    }

    @Test
    public void testVerifyAtLeastOnce() {
        // Act
        service.fetchMultipleData();

        // Verify getData was called at least once
        verify(mockApi, atLeastOnce()).getData(anyString());
    }

    @Test
    public void testVerifyAtMost() {
        // Act
        service.fetchData();

        // Verify getData was called at most 2 times
        verify(mockApi, atMost(2)).getData();
    }

    @Test
    public void testVerifyWithTimeout() {
        // Setup mock to return value
        when(mockApi.getData()).thenReturn("async data");

        // Act
        service.fetchData();

        // Verify within timeout (useful for async operations)
        verify(mockApi, timeout(1000)).getData();
    }
}