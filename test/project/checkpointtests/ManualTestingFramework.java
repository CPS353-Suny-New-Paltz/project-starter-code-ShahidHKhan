package project.checkpointtests;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataComputeAPIImpl;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;

import java.util.Collections;
import java.util.List;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {

        DataComputeAPI data = new DataComputeAPIImpl();
        InterComputeAPI inter = new InterComputeAPIImpl();
        UserComputeAPI user = new UserComputeAPIImpl(inter, data);

        List<Integer> inputNumbers = data.readInput(INPUT);
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            System.err.println("No valid input found.");
            return;
        }

        ComputeRequest request = new ComputeRequest(inputNumbers, OUTPUT);

        ComputeResponse response = user.compute(request);

        System.out.println("Success: " + response.isSuccess() + ", result=" + response.getResult());
    }
}
