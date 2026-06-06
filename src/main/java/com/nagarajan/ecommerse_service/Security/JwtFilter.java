package com.nagarajan.ecommerse_service.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtill jwtUtill;
    @Autowired
    private CustomeUserDetails customeUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       String header=request.getHeader("Authorization");
       String path=request.getServletPath();
       String username=null;
       String token=null;
     if(path.startsWith("/auth")){
         filterChain.doFilter(request,response);
         return;
     }
     if(header!=null && header.startsWith("Bearer ")){
         token=header.substring(7);
         username= jwtUtill.extractname(token);
     }
     if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
         UserDetails userDetails = customeUserDetails.loadUserByUsername(username);
         if (jwtUtill.ValidateToken(token, userDetails.getUsername())) {
             UsernamePasswordAuthenticationToken auth =
                     new UsernamePasswordAuthenticationToken(userDetails,
                             null,
                             userDetails.getAuthorities());
             auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(auth);
         }
     }
         filterChain.doFilter(request,response);

    }
}
