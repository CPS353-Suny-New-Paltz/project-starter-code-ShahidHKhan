package project.networkcompute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadedNetworkAPI {

    private final ExecutorService executor;

    public MultithreadedNetworkAPI() {
        int maxThreads = 4; // DOCUMENT THIS IN README
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    public List<String> processRequests(List<String> requests) {
        List<Future<String>> futures = new ArrayList<>();

        for (String request : requests) {
            futures.add(executor.submit(() -> "processed:" + request));
        }

        List<String> results = new ArrayList<>();
        for (Future<String> future : futures) {
            try {
                results.add(future.get());
            } catch (Exception e) {
                results.add("error");
            }
        }

        return results;
    }

    public void shutdown() {
        executor.shutdown();
    }
}
