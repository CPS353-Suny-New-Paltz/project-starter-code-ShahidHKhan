package project.api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface InterComputeAPI {

	void initializeJob(String jobId);

    /** Get the next batch of integers to process. Returns null or empty when done. */
    int[] fetchNextBatch();

    /** Submit computed results for the current/previous batch. */
    void submitResults(int[] results);

    /** Finish the job (flush/close). */
    void finalizeJob();
}
