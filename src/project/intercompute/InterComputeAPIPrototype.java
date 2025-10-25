package project.intercompute;

import project.annotations.ConceptualAPIPrototype;

public class InterComputeAPIPrototype {
	@ConceptualAPIPrototype
    public void prototype(InterComputeAPI inter) {
        // Validate input parameter
        if (inter == null) {
            throw new IllegalArgumentException("InterComputeAPI instance cannot be null.");
        }
        InterComputeAPI compute = inter;
        compute.processRequest(new InterRequest("Hello from InterCompute".getBytes()));
        compute.processRequest(new InterRequest(new byte[]{42, 43, 44}));
    }
}