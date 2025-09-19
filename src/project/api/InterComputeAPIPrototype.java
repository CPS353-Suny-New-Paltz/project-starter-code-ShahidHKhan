package project.api;

import java.util.Arrays;
import project.annotations.ConceptualAPIPrototype;

public class InterComputeAPIPrototype implements InterComputeAPI{
	
	private String currentJob;
    private int cursor = 0;
    private final int[][] fakeBatches = {
        {1, 2, 3}, {4, 5, 6}, {} // empty marks "no more work"
    };

    @Override
    @ConceptualAPIPrototype
    public void initializeJob(String jobId) {
        this.currentJob = jobId;
        this.cursor = 0;
        System.out.println("ConceptualAPI Prototype: initialize job = " + jobId);
    }

    @Override
    @ConceptualAPIPrototype
    public int[] fetchNextBatch() {
        int[] batch = (cursor < fakeBatches.length) ? fakeBatches[cursor++] : new int[0];
        System.out.println("ConceptualAPI Prototype: fetchNextBatch -> " + Arrays.toString(batch));
        return batch;
    }

    @Override
    @ConceptualAPIPrototype
    public void submitResults(int[] results) {
        System.out.println("ConceptualAPI Prototype: submitResults -> " + Arrays.toString(results));
    }

    @Override
    @ConceptualAPIPrototype
    public void finalizeJob() {
        System.out.println("ConceptualAPI Prototype: finalize job = " + currentJob);
        currentJob = null;
    }
}
