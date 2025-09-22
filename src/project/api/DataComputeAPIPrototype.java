package project.api;

import java.util.Arrays;
import project.annotations.ProcessAPIPrototype;

public class DataComputeAPIPrototype implements DataComputeAPI {

	private String input;
    private String output;

    @Override
    @ProcessAPIPrototype
    public void configure(String input, String output) {
        this.input = input;
        this.output = output;
        System.out.println("ProcessAPI Prototype: configured input=" + input + " output=" + output);
    }

    @Override
    @ProcessAPIPrototype
    public int[] readBatch() {
        int[] fake = {1, 2, 3, 4, 5};
        System.out.println("ProcessAPI Prototype: read from " + input + " -> " + Arrays.toString(fake));
        return fake;
    }

    @Override
    @ProcessAPIPrototype
    public void writeBatch(int[] batch) {
        System.out.println("ProcessAPI Prototype: wrote to " + output + " -> " + Arrays.toString(batch));
    }
}
