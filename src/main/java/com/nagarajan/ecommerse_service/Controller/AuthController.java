package com.nagarajan.ecommerse_service.Controller;

import com.nagarajan.ecommerse_service.Model.Login;
import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Model.User.UserRequest;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
import com.nagarajan.ecommerse_service.Security.JwtUtill;
import com.nagarajan.ecommerse_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo repo;
    @Autowired
    private JwtUtill jwtUtill;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request){
        if(repo.findByEmail(request.getEmail()).isPresent()){
            return new ResponseEntity<>("Email already register",HttpStatus.CONFLICT);
        }
        service.createUser(request);
        return new ResponseEntity<>(" Successfully Register",HttpStatus.CREATED);
    }

    @PostMapping("/login")
      public ResponseEntity<?> Login(@RequestBody Login login){
      var finduser=repo.findByEmail(login.getEmail());
      if(finduser.isEmpty()){
          return new ResponseEntity<>("User Not Register",HttpStatus.UNAUTHORIZED);
      }
      User user=finduser.get();
      if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
          return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
      }
      String token= jwtUtill.genderateToken(login.getEmail());
      return ResponseEntity.ok(Map.of("token",token));
    }
}
