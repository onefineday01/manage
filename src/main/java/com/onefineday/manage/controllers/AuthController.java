package com.onefineday.manage.controllers;


import com.onefineday.manage.dto.ApiResponse;
import com.onefineday.manage.models.User;
import com.onefineday.manage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<Object>> registerUser(@RequestBody User user) {
    System.out.println(user);
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            userService.registerUser(user);
            response.setData(user);  // Empty list for data in this case
            response.setSuccess(true);
            response.setErrors(Collections.emptyList());  // No errors
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData(Collections.emptyList());
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/profile")
    public User getUserDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserDetails(username);
    }
}
