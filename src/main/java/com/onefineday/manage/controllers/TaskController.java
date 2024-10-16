package com.onefineday.manage.controllers;

import com.onefineday.manage.models.Task;
import com.onefineday.manage.services.TaskService;
import com.onefineday.manage.utility.ApiResponse;
import com.onefineday.manage.utility.PaginatedResponse;
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
    public ResponseEntity<ApiResponse<PaginatedResponse<Task>>> getTasks(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount
    ) {
        return ResponseEntity.ok(new ApiResponse<>(taskService.getTasks(search, sortBy, sortOrder, pageNo -1 , pageCount), true, Collections.emptyList()));
    }
}
