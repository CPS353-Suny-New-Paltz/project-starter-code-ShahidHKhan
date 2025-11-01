package project.checkpointtests;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataComputeAPIImpl;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.DataSource;

import java.util.List;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
    
        DataComputeAPI data = new DataComputeAPIImpl();
        InterComputeAPI inter = new InterComputeAPIImpl();      
        UserComputeAPI user = new UserComputeAPIImpl(inter, data);

      
        DataSource fileSource = () -> {
            List<Integer> nums = data.readInput(INPUT);
            return (nums == null || nums.isEmpty()) ? -1 : nums.get(0);
        };

        
        ComputeRequest request = new ComputeRequest(fileSource, OUTPUT);
        ComputeResponse response = user.compute(request);

        System.out.println("Success: " + response.isSuccess() + ", result=" + response.getResult());
    }
}
