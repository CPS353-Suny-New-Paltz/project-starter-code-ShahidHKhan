package intercompute;

import java.util.Arrays;

import project.annotations.ConceptualAPIPrototype;

public class InterComputeAPIPrototype {

	@ConceptualAPIPrototype
    public void prototype(InterComputeAPI inter) {
    	InterComputeAPI compute = inter;

        compute.insertRequest(new InterRequest("Hello from InterCompute".getBytes()));

        compute.insertRequest(new InterRequest(new byte[]{42, 43, 44}));
    }
}