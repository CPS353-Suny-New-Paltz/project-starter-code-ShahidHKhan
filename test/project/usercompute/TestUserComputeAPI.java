package project.usercompute;

import org.junit.jupiter.api.Test;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.Collections;

public class TestUserComputeAPI {

    private static UserComputeAPI sut(InterComputeAPI inter, DataComputeAPI data) {
        return new UserComputeAPIImpl(inter, data);
    }

    @Test
    void handleRequest_callsInter() {
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data   = mock(DataComputeAPI.class);
        UserComputeAPI user   = sut(inter, data);

        user.handleRequest(new UserRequest(99));

        verify(inter).processRequest(any(InterRequest.class));
        verifyNoMoreInteractions(inter);
        verifyNoInteractions(data);
    }

    @Test
    void compute_ok_writesOutput() {
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data   = mock(DataComputeAPI.class);
        when(inter.processRequest(any(InterRequest.class))).thenReturn(23);

        UserComputeAPI user = sut(inter, data);

        ComputeResponse resp = user.compute(new ComputeRequest(() -> 25, "out.csv"));

        assertTrue(resp.isSuccess());
        assertEquals(23, resp.getResult());
        verify(inter).processRequest(any(InterRequest.class));
        verify(data).writeOutput(eq(Collections.singletonList(23)), eq("out.csv"));
        verifyNoMoreInteractions(inter, data);
    }

    @Test
    void compute_badInput_fails() {
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data   = mock(DataComputeAPI.class);
        UserComputeAPI user   = new UserComputeAPIImpl(inter, data);

        ComputeResponse r1 = user.compute(null);

        DataSource badSrc = () -> { throw new RuntimeException("boom"); };
        ComputeResponse r2 = user.compute(new ComputeRequest(badSrc, "out.csv"));

        assertFalse(r1.isSuccess());
        assertFalse(r2.isSuccess());
        verifyNoInteractions(inter, data);
    }


    @Test
    void compute_ok_noOut_doesNotWrite() {
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data   = mock(DataComputeAPI.class);
        when(inter.processRequest(any(InterRequest.class))).thenReturn(7);

        UserComputeAPI user = sut(inter, data);

        ComputeResponse resp = user.compute(new ComputeRequest(() -> 10, null));

        assertTrue(resp.isSuccess());
        assertEquals(7, resp.getResult());
        verify(inter).processRequest(any(InterRequest.class));
        verifyNoInteractions(data);
    }
}

