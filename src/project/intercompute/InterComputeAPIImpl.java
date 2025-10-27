package project.intercompute;

import java.util.ArrayList;
import java.util.List;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class InterComputeAPIImpl implements InterComputeAPI {

    private final DataComputeAPI data;

    public InterComputeAPIImpl(DataComputeAPI data) {
    	// Validate dependency (internal layer — throws instead of catching)
        if (data == null) {
            throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        }
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
        // Validate parameter
        if (req == null) {
            throw new IllegalArgumentException("InterRequest cannot be null.");
        }
        if (data == null) {
            throw new IllegalStateException("DataComputeAPI dependency is not initialized.");
        }

        byte[] bytes = req.getBytes();
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("InterRequest bytes cannot be null or empty.");
        }

        int n = bytes[0];

        int largestPrime = LargestPrime.largestPrimeLeq(n);

        // Store result
        String result = String.valueOf(largestPrime);
        data.insertRequest(new DataRequest(result.getBytes()));
    }

    @Override
    public List<String> computeAll(List<Integer> ns) {
        
    	if (ns == null || ns.isEmpty()) {
    	    throw new IllegalArgumentException("Input list 'ns' cannot be null or empty.");
    	}

        List<String> out = new ArrayList<>();
        for (Integer n : ns) {
            if (n == null || n < 2) {
                out.add("none");
                continue;
            }
            int p = LargestPrime.largestPrimeLeq(n);
            out.add(p >= 2 ? String.valueOf(p) : "none");
        }
        return out;
    }
}
