package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Model.User.UserRequest;
import com.nagarajan.ecommerse_service.Model.User.UserResponse;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
import com.nagarajan.ecommerse_service.ServicesImpl.UserServeImp;
import com.nagarajan.ecommerse_service.Specification.UserSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServeImp {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo repo;
    public UserResponse createUser(UserRequest request) {
        User user=mapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repo.save(user);
        return mapper.map(user,UserResponse.class);
    }
    public UserResponse UpdateUser(long id, UserRequest request) {
        User user=repo.findById(id).orElseThrow();
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        repo.save(user);
        return mapper.map(user,UserResponse.class);
    }
    public void DeleteUserById(long id) {
        repo.deleteById(id);
    }
    public List<UserResponse> GetAllUser(int page, int size, String sortby, String direction,
                                         String name,String address) {
        Sort sort=null;
        if(!direction.equalsIgnoreCase("DESC")){
            sort=Sort.by(sortby).descending();
        }
        if(!direction.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortby).ascending();
        }
        else{
            sort=Sort.by(sortby).ascending();
        }
        PageRequest pageRequest=PageRequest.of(page-1,size,sort);
        Specification<User> specification=new UserSpecification(name,address);
        Page<User> page1=repo.findAll(specification,pageRequest);
        return page1.getContent()
                    .stream()
                    .map(page2->mapper.map(page2,UserResponse.class))
                    .toList();
    }
    public UserResponse GetByUserId(long id) {
        User user=repo.findById(id).orElseThrow();
        return mapper.map(user,UserResponse.class);
    }
    public UserResponse GetByUserName(String name) {
        Optional<User> user=repo.findByName(name);
        return mapper.map(user,UserResponse.class);
    }
}
