package project.usercompute;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class TestUserComputeAPI {

    @Test
    void smoke_handleRequest_withMockedInterLayer() {
        InterComputeAPI mockInter = mock(InterComputeAPI.class);
        // If InterComputeAPI returns boolean, you can stub:
        // when(mockInter.processRequest(any(InterRequest.class))).thenReturn(true);

        UserComputeAPIimpl user = new UserComputeAPIimpl();
        user.setInter(mockInter); // requires the simple setter mentioned above

        UserRequest req = new UserRequest("u1", "hello");
        user.handleRequest(req);

        verify(mockInter, atLeastOnce()).processRequest(any(InterRequest.class));
    }
}