package project.usercompute;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {

    @NetworkAPIPrototype
    public void prototype(UserComputeAPI user) {
        // Validate parameter manually
        if (user == null) {
            throw new IllegalArgumentException("UserComputeAPI instance cannot be null.");
        }

        // No additional validation needed
        UserComputeAPI compute = user;

        // Example test requests using integer values
        compute.handleRequest(new UserRequest(10));
        compute.handleRequest(new UserRequest(25));
        compute.handleRequest(new UserRequest(97));
    }
}
