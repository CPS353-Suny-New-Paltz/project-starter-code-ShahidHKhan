package project.usercompute;

import java.util.List;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

public class UserComputeAPIImpl implements UserComputeAPI {

    private final InterComputeAPI inter;
    private final DataComputeAPI data;

    public UserComputeAPIImpl(InterComputeAPI inter, DataComputeAPI data) {
        // ✅ Safe constructor: don’t throw; plug in default no-op fallbacks
        this.inter = (inter != null) ? inter : NoopInterComputeAPI.INSTANCE;
        this.data  = (data  != null) ? data  : NoopDataComputeAPI.INSTANCE;
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
        	// intentionally ignore invalid user input 
        } catch (Exception e) {
        	// intentionally swallow unexpected exceptions
        }
    }
    static final class NoopInterComputeAPI implements InterComputeAPI {
        static final NoopInterComputeAPI INSTANCE = new NoopInterComputeAPI();
        private NoopInterComputeAPI() {}
        @Override
        public void processRequest(InterRequest req) {
            // do nothing
        }
        @Override
        public List<String> computeAll(List<Integer> ns) {
            return java.util.Collections.emptyList();
        }
    }

    static final class NoopDataComputeAPI implements DataComputeAPI {
        static final NoopDataComputeAPI INSTANCE = new NoopDataComputeAPI();
        private NoopDataComputeAPI() {}
        @Override
        public List<Integer> readInput(String inputPath) {
            return java.util.Collections.emptyList();
        }
        @Override
        public void writeOutput(List<String> out, String outputPath) {
            
        }
        @Override
        public void insertRequest(DataRequest dataRequest) {
            
        }
    }
    
}

