package project.intercompute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BenchmarkInterComputeTest {

    // Benchmark utility
	private long benchmark(InterComputeAPI engine, int n) {
	    long start = System.nanoTime();

	    // Run the computation MANY times to simulate real load
	    for (int i = 0; i < 500; i++) {
	        engine.processRequest(new InterRequest(n));
	    }

	    long end = System.nanoTime();
	    return end - start;
	}


    @Test
    void fastVersionIsAtLeast10PercentFaster() {
        InterComputeAPI original = new InterComputeAPIImpl();
        InterComputeAPI fast = new FastInterComputeAPIImpl();

        int testValue = 100_000;  // large enough to measure difference

        long originalTime = benchmark(original, testValue);
        long fastTime = benchmark(fast, testValue);

        System.out.println("Original: " + originalTime + " ns");
        System.out.println("Fast: " + fastTime + " ns");

        //fast version must be >=10% faster
        assertTrue(
            fastTime <= originalTime * 0.90,
            "Fast version must be at least 10% faster.\n" +
            "Original: " + originalTime + " ns\n" +
            "Fast: " + fastTime + " ns\n"
        );
    }
}
