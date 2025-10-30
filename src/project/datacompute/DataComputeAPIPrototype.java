package project.datacompute;

import project.annotations.ProcessAPIPrototype;

public class DataComputeAPIPrototype {

    @ProcessAPIPrototype
    public void prototype(DataComputeAPI storage) {
        // Validate input parameter
        if (storage == null) {
            throw new IllegalArgumentException("DataComputeAPI instance cannot be null.");
        }

        DataComputeAPI compute = storage;

        // Example test requests using integer values
        compute.insertRequest(new DataRequest(10));
        compute.insertRequest(new DataRequest(42));
        compute.insertRequest(new DataRequest(99));
    }
}
