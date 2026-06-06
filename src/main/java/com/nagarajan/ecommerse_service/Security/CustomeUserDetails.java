package com.nagarajan.ecommerse_service.Security;

import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomeUserDetails  implements UserDetailsService {

    @Autowired
    UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=repo.findByEmail(email).orElseThrow(()->
                            new UsernameNotFoundException(email+" is Not Found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}
