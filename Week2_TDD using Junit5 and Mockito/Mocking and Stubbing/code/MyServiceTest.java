package com.example.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void testFetchData_Success() {
        // Arrange: Stub the mock methods
        when(mockApi.isServiceAvailable()).thenReturn(true);
        when(mockApi.getData()).thenReturn("Mock Data");

        // Act: Call the method under test
        String result = service.fetchData();

        // Assert: Verify the result
        assertEquals("Mock Data", result);

        // Verify that the mock methods were called
        verify(mockApi).isServiceAvailable();
        verify(mockApi).getData();
    }

    @Test
    public void testFetchData_ServiceUnavailable() {
        // Arrange: Stub service as unavailable
        when(mockApi.isServiceAvailable()).thenReturn(false);

        // Act
        String result = service.fetchData();

        // Assert
        assertEquals("Service unavailable", result);

        // Verify that getData() was never called
        verify(mockApi).isServiceAvailable();
        verify(mockApi, never()).getData();
    }

    @Test
    public void testFetchDataById_ValidId() {
        // Arrange: Stub method with specific parameter
        when(mockApi.getDataById(1)).thenReturn("Data for ID 1");

        // Act
        String result = service.fetchDataById(1);

        // Assert
        assertEquals("Data for ID 1", result);

        // Verify method call with specific argument
        verify(mockApi).getDataById(1);
    }

    @Test
    public void testFetchDataById_InvalidId() {
        // Act: Test with invalid ID
        String result = service.fetchDataById(-1);

        // Assert
        assertEquals("Invalid ID", result);

        // Verify that the external API was never called
        verify(mockApi, never()).getDataById(anyInt());
    }

    @Test
    public void testGetServiceStatus_Available() {
        // Arrange
        when(mockApi.isServiceAvailable()).thenReturn(true);

        // Act
        String status = service.getServiceStatus();

        // Assert
        assertEquals("Available", status);
        verify(mockApi).isServiceAvailable();
    }

    @Test
    public void testGetServiceStatus_Unavailable() {
        // Arrange
        when(mockApi.isServiceAvailable()).thenReturn(false);

        // Act
        String status = service.getServiceStatus();

        // Assert
        assertEquals("Unavailable", status);
        verify(mockApi).isServiceAvailable();
    }
}