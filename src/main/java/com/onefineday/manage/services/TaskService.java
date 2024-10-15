package com.onefineday.manage.services;

import com.onefineday.manage.models.*;
import com.onefineday.manage.repositories.TaskRepository;
import com.onefineday.manage.repositories.UserRepository;
import com.onefineday.manage.utility.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public void createTask(Task task) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setUser(userRepository.findByUsername(userDetails.getUsername()));
        taskRepository.save(task);
    }

    public Task updateTask(Long id, Map<String, Object> taskDetails) {
        // Fetch the existing task
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (taskDetails.containsKey("title")) {
            task.setTitle((String) taskDetails.get("title"));
        }
        if (taskDetails.containsKey("description")) {
            task.setDescription((String) taskDetails.get("description"));
        }
        if (taskDetails.containsKey("dueDate")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            task.setDueDate(LocalDate.parse((String) taskDetails.get("dueDate"), formatter));
        }
        if (taskDetails.containsKey("status")) {
            task.setStatus(Status.valueOf((String) taskDetails.get("status")));
        }
        if (taskDetails.containsKey("priority")) {
            task.setPriority(Priority.valueOf((String) taskDetails.get("priority")));
        }
        if (taskDetails.containsKey("category")) {
            task.setCategory((String) taskDetails.get("category"));
        }
        // Save the updated task
        return taskRepository.save(task);
    }

    public List<Task> getTasks() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Role userRole = Role.valueOf(user.getRole());
        if(userRole.equals(Role.valueOf("ADMIN"))) {
            return taskRepository.findAll();
        }
        return taskRepository.findAllByUserId(user.getId());
    }
}