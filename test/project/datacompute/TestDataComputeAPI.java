package project.datacompute;

import org.junit.jupiter.api.Test;

public class TestDataComputeAPI {

    @Test
    void smoke_insertRequest_runs() {
        DataComputeAPI api = new DataComputeAPIimpl();
        DataRequest req = new DataRequest(new byte[]{1, 2, 3});
        api.insertRequest(req);
        // No assertions yet; goal = it compiles & runs
    }
}