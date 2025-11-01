package project.usercompute;

import java.util.List;
import java.util.Collections;

import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class UserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;
    private final DataComputeAPI data;

    public UserComputeAPIImpl(InterComputeAPI inter, DataComputeAPI data) {
        if (inter == null) throw new IllegalArgumentException("InterComputeAPI cannot be null.");
        if (data == null)  throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        this.inter = inter;
        this.data = data;
    }

    @Override
    public ComputeResponse compute(ComputeRequest request) {
        try {
            if (request == null || request.getSource() == null) {
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            int n = request.getSource().get();
            int result = inter.processRequest(new InterRequest(n));

            if (request.getOutputPath() != null) {
                data.writeOutput(java.util.Collections.singletonList(result), request.getOutputPath());
            }

            return new ComputeResponse(result, ComputeResponse.Status.SUCCESS);

        } catch (Exception e) {
            System.err.println("UserComputeAPIImpl.compute error: " + e.getMessage());
            return new ComputeResponse(0, ComputeResponse.Status.FAIL);
        }
    }


    @Override
    public void handleRequest(UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("userRequest cannot be null.");
        }
        inter.processRequest(new InterRequest(userRequest.getNumber()));
    }
}
