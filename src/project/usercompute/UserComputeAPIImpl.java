package project.usercompute;

import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class UserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;

    public UserComputeAPIImpl(InterComputeAPI inter) {
        this.inter = inter;
    }

    @Override
    public void handleRequest(UserRequest req) {
        if (inter == null || req == null) {
            return;
        }
        // If your UserRequest uses a different accessor, replace getBytes()
        inter.processRequest(new InterRequest(req.getBytes()));
    }
}