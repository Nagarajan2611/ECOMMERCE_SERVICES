package com.nagarajan.ecommerse_service.ServicesImpl;

import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Model.User.UserRequest;
import com.nagarajan.ecommerse_service.Model.User.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserServeImp {
    UserResponse createUser(UserRequest request);
    UserResponse UpdateUser(long id,UserRequest request);
    void DeleteUserById(long id);
    List<UserResponse> GetAllUser(int page, int size, String sortby, String direction, String name,String address);
    UserResponse GetByUserId(long id);
    UserResponse GetByUserName(String name);
}
