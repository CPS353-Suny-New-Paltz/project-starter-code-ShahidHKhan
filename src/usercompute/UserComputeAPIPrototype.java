package usercompute;

import project.annotations.NetworkAPIPrototype;


public class UserComputeAPIPrototype {

	@NetworkAPIPrototype
    public void prototype(UserComputeAPI user) {
    	UserComputeAPI compute = user;
    	
        compute.insertRequest(new UserRequest("Hello from UserCompute".getBytes()));

        compute.insertRequest(new UserRequest(new byte[]{10, 20, 30}));
    }
}