package project.datacompute;

import java.util.Arrays; 
import project.annotations.ProcessAPIPrototype; 
public class DataComputeAPIPrototype { 
	@ProcessAPIPrototype
    public void prototype(DataComputeAPI storage) {
        // Validate input parameter
        if (storage == null) {
            throw new IllegalArgumentException("DataComputeAPI instance cannot be null.");
        } 
		DataComputeAPI compute = storage; 
		compute.insertRequest(new DataRequest("Hello world".getBytes())); 
		compute.insertRequest(new DataRequest(new byte[]{1, 2, 3, 4, 5})); 
	} 
}
