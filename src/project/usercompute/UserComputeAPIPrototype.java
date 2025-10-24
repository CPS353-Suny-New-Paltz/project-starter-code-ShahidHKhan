package project.usercompute;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
	
	@NetworkAPIPrototype
    public void prototype(UserComputeAPI user) {
        // Validate parameter manually
        if (user == null) {
            throw new IllegalArgumentException("UserComputeAPI instance cannot be null.");
        }
        //no validation needed
        UserComputeAPI compute = user;
        compute.handleRequest(new UserRequest("Hello from UserCompute".getBytes()));
        compute.handleRequest(new UserRequest(new byte[]{10, 20, 30}));
    }
}