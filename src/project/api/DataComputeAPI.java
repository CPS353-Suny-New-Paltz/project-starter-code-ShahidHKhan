package project.api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataComputeAPI {

	void configure(String input, String output);

    int[] readBatch();   // for now: just return an array
    void writeBatch(int[] batch);
}
