package project.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import project.usercompute.UserComputeAPIImpl;
import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;

public class ExceptionHandlingTest {

    @Test
    void handleInterLayeThrows() {
        // arrange
        DataComputeAPI data = mock(DataComputeAPI.class);
        InterComputeAPI inter = mock(InterComputeAPI.class);

        when(data.readInput("input.txt")).thenReturn(List.of(1, 2, 3));
        when(inter.computeAll(any())).thenThrow(new RuntimeException("boom"));

        UserComputeAPIImpl api = new UserComputeAPIImpl(inter, data);

        // act
        boolean result = api.handle("input.txt", "output.txt");

        // assert
        assertFalse(result, "API should translate exception to a sentinel false");
        verify(data, never()).writeOutput(any(), any());
    }

    @Test
    void handleDataLayeThrows() {
        // arrange
        DataComputeAPI data = mock(DataComputeAPI.class);
        InterComputeAPI inter = mock(InterComputeAPI.class);

        when(data.readInput("input.txt")).thenThrow(new RuntimeException("read failed"));

        UserComputeAPIImpl api = new UserComputeAPIImpl(inter, data);

        // act
        boolean result = api.handle("input.txt", "output.txt");

        // assert
        assertFalse(result);
        verify(inter, never()).computeAll(any());
    }
}
