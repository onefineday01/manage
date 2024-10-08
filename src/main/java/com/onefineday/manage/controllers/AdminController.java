package com.onefineday.manage.controllers;


import com.onefineday.manage.utility.ApiResponse;
import com.onefineday.manage.models.User;
import com.onefineday.manage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        ApiResponse<List<User>> userList = new ApiResponse<>();
        userList.setData(userService.getAllUsers());
        userList.setSuccess(true);
        userList.setErrors(Collections.emptyList());
        return ResponseEntity.ok(userList);
    }
}
