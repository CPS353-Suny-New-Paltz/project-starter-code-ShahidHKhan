package project.usercompute;

import java.util.List;

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
        this.data = data;
    }

    @Override
    public boolean handle(String inputPath, String outputPath) {
        
        try {
            
            if (inputPath == null || inputPath.isBlank()) {
                throw new IllegalArgumentException("inputPath cannot be null or blank.");
            }
            if (outputPath == null || outputPath.isBlank()) {
                throw new IllegalArgumentException("outputPath cannot be null or blank.");
            }

            
            List<Integer> ns = data.readInput(inputPath);
            if (ns == null) {
                throw new IllegalStateException("DataComputeAPI returned null for readInput().");
            }

            List<String> results = inter.computeAll(ns);
            data.writeOutput(results, outputPath);
            return true; 
        } catch (IllegalArgumentException e) {
            
            return false;
        } catch (Exception e) {
            
            return false;
        }
    }

    @Override
    public void handleRequest(UserRequest userRequest) {
        
        try {
            if (userRequest == null) {
                throw new IllegalArgumentException("userRequest cannot be null.");
            }
            inter.processRequest(new InterRequest(userRequest.getBytes()));
        } catch (IllegalArgumentException e) {
            
        } catch (Exception e) {
            
        }
    }
}
