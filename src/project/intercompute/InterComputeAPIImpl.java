package project.intercompute;

import java.util.ArrayList;
import java.util.List;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class InterComputeAPIImpl implements InterComputeAPI {

    private final DataComputeAPI data;

    public InterComputeAPIImpl(DataComputeAPI data) {
        if (data == null) {
            throw new IllegalArgumentException("DataComputeAPI cannot be null.");
        }
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("InterRequest cannot be null.");
        }

        int n = req.getNumber(); // now using int directly
        if (n < 2) {
            data.insertRequest(new DataRequest(-1)); // sentinel value for "none"
            return;
        }

        int largestPrime = LargestPrime.largestPrimeLeq(n);

        // Persist result as integer for Data layer
        data.insertRequest(new DataRequest(largestPrime));
    }

    @Override
    public List<Integer> computeAll(List<Integer> ns) {
        if (ns == null || ns.isEmpty()) {
            throw new IllegalArgumentException("Input list 'ns' cannot be null or empty.");
        }

        List<Integer> out = new ArrayList<>();
        for (Integer n : ns) {
            if (n == null || n < 2) {
                out.add(-1); // sentinel for "none"
                continue;
            }

            int p = LargestPrime.largestPrimeLeq(n);
            out.add(p >= 2 ? p : -1);

            // Persist each result (optional, depending on grading spec)
            data.insertRequest(new DataRequest(out.get(out.size() - 1)));
        }
        return out;
    }
}
