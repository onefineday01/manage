package com.onefineday.manage.controllers;


import com.onefineday.manage.models.Task;
import com.onefineday.manage.utility.ApiResponse;
import com.onefineday.manage.models.User;
import com.onefineday.manage.services.UserService;
import com.onefineday.manage.utility.PaginatedResponse;
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
    public ResponseEntity<ApiResponse<PaginatedResponse<User>>> getAllUsers(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount
    ) {
        return ResponseEntity.ok(new ApiResponse<>(userService.getAllUsers(search, sortBy, sortOrder, pageNo -1 , pageCount), true, Collections.emptyList()));
    }
}
