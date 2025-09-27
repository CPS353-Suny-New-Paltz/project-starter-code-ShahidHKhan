package project.datacompute;

import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;

public class InMemoryDataComputeAPI implements DataComputeAPI{
	
	private final InMemoryInpCon input;
    private final InMemoryOutCon output;

    public InMemoryDataComputeAPI(InMemoryInpCon input, InMemoryOutCon output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
        for (Integer n : input.getInts()) {
            output.write(String.valueOf(n));
        }
    }
}
