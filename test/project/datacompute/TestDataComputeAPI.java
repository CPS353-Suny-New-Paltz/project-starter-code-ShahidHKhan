package project.datacompute;

import org.junit.jupiter.api.Test;

public class TestDataComputeAPI {

    @Test
    void smokeInsertRequestRuns() {
        DataComputeAPI api = new DataComputeAPIImpl();
        DataRequest req = new DataRequest(42); // example integer request
        api.insertRequest(req);
    }
}
