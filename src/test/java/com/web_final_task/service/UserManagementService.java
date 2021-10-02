package com.web_final_task.service;

import com.web_final_task.entity.xml.CreateUserRequest;
import com.web_final_task.entity.xml.CreateUserResponse;
import com.web_final_task.entity.xml.DeleteUserRequest;
import com.web_final_task.entity.xml.DeleteUserResponse;
import com.web_final_task.entity.xml.GetUserDetailsRequest;
import com.web_final_task.entity.xml.GetUserDetailsResponse;
import com.web_final_task.entity.xml.UpdateUserRequest;
import com.web_final_task.entity.xml.UpdateUserResponse;

public class UserManagementService {

    private UsersPortService service = new UsersPortService();

    public CreateUserResponse createUserDetails(CreateUserRequest createUserRequest) {
       return service.getUsersPortSoap11().createUser(createUserRequest);
    }

    public UpdateUserResponse updateUserDetails(UpdateUserRequest userRequest) {
        return service.getUsersPortSoap11().updateUser(userRequest);
    }

    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        return service.getUsersPortSoap11().getUserDetails(request);
    }

    public DeleteUserResponse deleteUserDetails(DeleteUserRequest request) {
        return service.getUsersPortSoap11().deleteUser(request);
    }
}

