package com.onefineday.manage.controllers;


import com.onefineday.manage.security.AuthenticationRequest;
import com.onefineday.manage.security.AuthenticationResponse;
import com.onefineday.manage.services.UserDetailsServiceImpl;
import com.onefineday.manage.utility.ApiResponse;
import com.onefineday.manage.models.User;
import com.onefineday.manage.services.UserService;
import com.onefineday.manage.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<Object>> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            ApiResponse<Object> response = new ApiResponse<>(user, true, Collections.emptyList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(Collections.emptyList(), false, Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<User>> getUserDetails() {
        ApiResponse<User> userDetail = new ApiResponse<>(this.getCurrentUserDetails(), true, Collections.emptyList());
        return ResponseEntity.ok(userDetail);
    }

    public User getCurrentUserDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserDetails(username);

    }
}
