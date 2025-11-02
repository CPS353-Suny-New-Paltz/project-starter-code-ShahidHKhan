package project.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.DataSource;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;

public class ExceptionHandlingTest {

    @Test
    void userCompute_returnsFail_whenInterThrows_andDoesNotWrite() {
        InterComputeAPI inter = new InterComputeAPI() {
            @Override
            public int processRequest(InterRequest req) {
                throw new RuntimeException("Compute failure");
            }
        };

        class CapturingData implements DataComputeAPI {
            List<Integer> lastWrite = null;

            @Override
            public List<Integer> readInput(String inputPath) {
                return List.of(5);
            }

            @Override
            public void writeOutput(List<Integer> results, String outputPath) {
                lastWrite = new ArrayList<>(results);
            }
        }
        CapturingData data = new CapturingData();

        UserComputeAPI user = new UserComputeAPIImpl(inter, data);

        DataSource src = () -> List.of(5);
        ComputeRequest req = new ComputeRequest(src, "ignored.csv");

        ComputeResponse resp = user.compute(req);

        assertNotNull(resp);
        assertEquals(ComputeResponse.Status.FAIL, resp.getStatus());
        assertEquals(0, resp.getResult());
        assertEquals(null, data.lastWrite, "Storage should not be written on failure");
    }
}
