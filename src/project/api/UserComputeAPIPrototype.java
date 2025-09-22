package project.api;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype implements UserComputeAPI {

    @Override
    @NetworkAPIPrototype
    public void setInputSource(String source) {
        // For now, just simulate
        System.out.println("Prototype: Input source set to " + source);
    }

    @Override
    @NetworkAPIPrototype
    public void setOutputDestination(String destination) {
        System.out.println("Prototype: Output destination set to " + destination);
    }

    @Override
    @NetworkAPIPrototype
    public void setDelimiters(String delimiters) {
        if (delimiters == null || delimiters.isEmpty()) {
            delimiters = ","; // default
        }
        System.out.println("Prototype: Delimiters set to '" + delimiters + "'");
    }
}