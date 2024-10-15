package com.onefineday.manage.controllers;

import com.onefineday.manage.models.Task;
import com.onefineday.manage.services.TaskService;
import com.onefineday.manage.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> createTask(@RequestBody Task task) {
        try {
            taskService.createTask(task);
            ApiResponse<Object> response = new ApiResponse<>(task, true, Collections.emptyList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(Collections.emptyList(), false, Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @RequestBody Map<String, Object> taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(new ApiResponse<>(updatedTask, true, Collections.emptyList()));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Task>>> getTasks() {
        return ResponseEntity.ok(new ApiResponse<>(taskService.getTasks(), true, Collections.emptyList()));
    }
}
