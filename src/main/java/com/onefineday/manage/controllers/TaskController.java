package com.onefineday.manage.controllers;

import com.onefineday.manage.models.Task;
import com.onefineday.manage.services.TaskService;
import com.onefineday.manage.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> createTask(@RequestBody Task task) {

        // System.out.println(user);
        ApiResponse<Object> response = new ApiResponse<>();
        try {

            taskService.createTask(task);
            response.setData(task);  // Empty list for data in this case
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

}
