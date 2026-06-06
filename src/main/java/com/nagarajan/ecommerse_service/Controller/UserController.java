package com.nagarajan.ecommerse_service.Controller;

import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Model.User.UserRequest;
import com.nagarajan.ecommerse_service.Model.User.UserResponse;
import com.nagarajan.ecommerse_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
//    @PostMapping
//    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
//         UserResponse response=service.createUser(request);
//         return new ResponseEntity<>(response,HttpStatus.CREATED);
//    }
    @PutMapping("/id/{id}")
    ResponseEntity<UserResponse> UpdateUser(@PathVariable long id,@RequestBody UserRequest request){
        UserResponse response=service.UpdateUser(id,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    ResponseEntity<String> DeleteUserById(@PathVariable long id){
        service.DeleteUserById(id);
        return new ResponseEntity<>("Deleted successfully"+id,HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<UserResponse>> GetAllUser(@RequestParam(required = false,defaultValue = "1") int page,
                                                  @RequestParam(required = false,defaultValue = "20") int size,
                                                  @RequestParam(required = false,defaultValue = "id") String sortby,
                                                  @RequestParam(required = false,defaultValue = "Asc") String direction,
                                                  @RequestParam(required = false)String name,
                                                  @RequestParam(required = false)String address){
        List<UserResponse> responses=service.GetAllUser(page,size,sortby,direction,name,address);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }@GetMapping("/id/{id}")
    ResponseEntity<UserResponse> GetByUserId(@PathVariable long id){
        UserResponse response=service.GetByUserId(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    ResponseEntity<UserResponse> GetByUserName(@PathVariable String name){
       UserResponse response= service.GetByUserName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
