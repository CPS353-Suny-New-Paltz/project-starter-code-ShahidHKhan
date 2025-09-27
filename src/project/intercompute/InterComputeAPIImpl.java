package project.intercompute;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class InterComputeAPIImpl implements InterComputeAPI {

    private final DataComputeAPI data;

    public InterComputeAPIImpl(DataComputeAPI data) {
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
        if (data == null || req == null) {
            return;
        }
        // If your InterRequest uses a different accessor, replace getBytes()
        data.insertRequest(new DataRequest(req.getBytes()));
    }
}