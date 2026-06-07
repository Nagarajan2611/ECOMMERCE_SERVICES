package com.nagarajan.ecommerse_service.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class logeddUser {
   public String loggedInUser(){
       return SecurityContextHolder.getContext()
               .getAuthentication()
               .getName();
   }

   public boolean Admin(){
       return SecurityContextHolder.getContext()
               .getAuthentication()
               .getAuthorities()
               .stream()
               .anyMatch(a->
                       a.getAuthority().equals("ROLE_ADMIN"));
   }
}
