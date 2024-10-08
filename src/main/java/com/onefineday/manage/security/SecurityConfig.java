package com.onefineday.manage.security;

import com.onefineday.manage.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF for API testing
                .authorizeHttpRequests((requests) -> requests
                    .anyRequest().authenticated()  // All endpoints need authentication
                ).httpBasic();  // Basic Authentication enabled
        return http.build();
    }
}