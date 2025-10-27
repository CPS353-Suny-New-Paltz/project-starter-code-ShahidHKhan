package project.usercompute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;

public class ValidationUserComputeAPITest {

    @Test
    void handle_invalidInputs_returnsFalse() {
        
        InterComputeAPI inter = mock(InterComputeAPI.class);
        DataComputeAPI data  = mock(DataComputeAPI.class);
        UserComputeAPIImpl api = new UserComputeAPIImpl(inter, data);

        boolean ok1 = api.handle(null, "out.txt");
        boolean ok2 = api.handle("   ", "out.txt");
        boolean ok3 = api.handle("input.txt", null);
        boolean ok4 = api.handle("input.txt", "   ");
     
        assertFalse(ok1);
        assertFalse(ok2);
        assertFalse(ok3);
        assertFalse(ok4);

        verifyNoInteractions(inter, data);
    }
}