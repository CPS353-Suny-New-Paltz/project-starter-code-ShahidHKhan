package project.intercompute;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class TestInterComputeAPI {

    @Test
    void smoke_processRequest_withMockedDataLayer() {
        DataComputeAPI mockData = mock(DataComputeAPI.class);
        // If DataComputeAPI has a boolean return, you can stub it like:
        // when(mockData.insertRequest(any(DataRequest.class))).thenReturn(true);

        InterComputeAPIimpl inter = new InterComputeAPIimpl();
        inter.setData(mockData); // requires the simple setter mentioned above

        InterRequest req = new InterRequest(new byte[]{9, 9});
        inter.processRequest(req);

        // Optional: verify the dependency got called
        verify(mockData, atLeastOnce()).insertRequest(any(DataRequest.class));
    }
}