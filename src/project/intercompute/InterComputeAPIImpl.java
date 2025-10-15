package project.intercompute;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class InterComputeAPIImpl implements InterComputeAPI {

    private final DataComputeAPI data;

    public InterComputeAPIImpl(DataComputeAPI data) {
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
        if (req == null) {
            return;
        }

        byte[] bytes = req.getBytes(); 
        if (bytes == null || bytes.length == 0) {
            data.insertRequest(new DataRequest("none".getBytes(StandardCharsets.UTF_8)));
            return;
        }

        int n = Byte.toUnsignedInt(bytes[0]);

        int largestPrime = LargestPrime.largestPrimeLeq(n);

        String result = (largestPrime >= 2) ? String.valueOf(largestPrime) : "none";
        data.insertRequest(new DataRequest(result.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public List<String> computeAll(List<Integer> ns) {
        List<String> out = new ArrayList<>();
        if (ns == null) {
            return out;
        }

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
