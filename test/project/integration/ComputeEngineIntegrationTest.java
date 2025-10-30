package project.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import project.datacompute.InMemoryDataComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.UserComputeAPIImpl;
import project.usercompute.UserRequest;

public class ComputeEngineIntegrationTest {

    @Test
    void runsEndToEndAndProducesExpectedOutput() {

        List<Integer> input = Arrays.asList(1, 10, 25);
        List<Integer> sink = new ArrayList<>();

        InMemoryInpCon inCfg = new InMemoryInpCon(input);
        InMemoryOutCon outCfg = new InMemoryOutCon(sink);

        InMemoryDataComputeAPI data = new InMemoryDataComputeAPI(inCfg, outCfg);
        InterComputeAPIImpl inter = new InterComputeAPIImpl(data);
        UserComputeAPIImpl user = new UserComputeAPIImpl(inter, data);

        user.handleRequest(new UserRequest(Integer.MAX_VALUE)); // or a large number, e.g., 1_000_000

        List<Integer> expected = Arrays.asList(1, 10, 25);

        assertEquals(expected, sink);
    }
}
