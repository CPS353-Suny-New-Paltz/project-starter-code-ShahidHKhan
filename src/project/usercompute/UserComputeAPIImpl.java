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
        if (inter == null) {
            throw new IllegalArgumentException("InterComputeAPI cannot be null.");
        }
        if (data == null) {
            throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        }
        this.inter = inter;
        this.data  = data;
    }

    @Override
    public ComputeResponse compute(ComputeRequest request) {
        try {
            if (request == null || request.getSource() == null) {
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            java.util.List<Integer> inputs = request.getSource().get();
            if (inputs == null || inputs.isEmpty()) {
                return new ComputeResponse(0, ComputeResponse.Status.FAIL);
            }

            java.util.List<Integer> outputs = new java.util.ArrayList<>(inputs.size());
            for (Integer n : inputs) {
                int val = (n == null) ? -1 : n.intValue();
                int result = inter.processRequest(new InterRequest(val));
                outputs.add(result);
            }

            String outPath = request.getOutputPath();
            if (outPath != null) {
                data.writeOutput(outputs, outPath);
            }

            return new ComputeResponse(outputs.get(outputs.size() - 1), ComputeResponse.Status.SUCCESS);

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
