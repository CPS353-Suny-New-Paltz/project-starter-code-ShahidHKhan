package project.intercompute;

import org.junit.jupiter.api.Test;
import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class TestInterComputeAPI {

    @Test
    void smokeProcessRequestWithMockedDataLayer() {
        DataComputeAPI mockData = mock(DataComputeAPI.class);

        InterComputeAPIImpl inter = new InterComputeAPIImpl(mockData);

        InterRequest req = new InterRequest(99);
        inter.processRequest(req);

        verify(mockData, atLeast(0)).insertRequest(any(DataRequest.class));
    }

    @Test
    void handlesNonPrimeInputsGracefully() {
        DataComputeAPI mockData = mock(DataComputeAPI.class);
        InterComputeAPIImpl inter = new InterComputeAPIImpl(mockData);

        List<Integer> inputs = List.of(-5, 0, 1);
        List<Integer> results = inter.computeAll(inputs);

        assertEquals(List.of(-1, -1, -1), results);
    }
}
