package project.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.datacompute.DataRequest;
import project.datacompute.InMemoryDataComputeAPI;
import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInMemoryIntegation {

    @Test
    void pipelineWritesAllIntegers() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> sink = new ArrayList<>();

        InMemoryInpCon inCfg = new InMemoryInpCon(input);
        InMemoryOutCon outCfg = new InMemoryOutCon(sink);

        InMemoryDataComputeAPI data = new InMemoryDataComputeAPI(inCfg, outCfg);

        data.insertRequest(new DataRequest(0));

        assertEquals(Arrays.asList(1, 2, 3), sink);
    }
}
