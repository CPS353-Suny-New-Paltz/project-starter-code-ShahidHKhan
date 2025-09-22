package usercompute;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    void insertRequest(UserRequest request);
}