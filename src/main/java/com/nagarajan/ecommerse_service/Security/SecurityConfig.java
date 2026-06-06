
    package com.nagarajan.ecommerse_service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Autowired
        private JwtFilter jwtFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{

              http
                    .csrf(csrf->csrf.disable())
                    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth->
                            auth.requestMatchers("/auth/**").permitAll()
                                    .requestMatchers(HttpMethod.GET,"/user/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.POST,"/user/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,"/user/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.GET,"/product/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.POST,"/product/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT,"/product/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,"/product/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.GET,"/cart/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.POST,"/cart/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.PUT,"/cart/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,"/cart/**").hasAnyRole("USER","ADMIN")

                                    .requestMatchers(HttpMethod.GET,"/order/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.POST,"/order/**").hasAnyRole("USER","ADMIN")
                                    .requestMatchers(HttpMethod.PUT,"/order/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE,"/order/**").hasRole("ADMIN")

                                    .anyRequest().authenticated())
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            return http. build();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    }


