package com.onefineday.manage.services;

import com.onefineday.manage.models.Role;
import com.onefineday.manage.models.User;
import com.onefineday.manage.repositories.UserRepository;
import com.onefineday.manage.utility.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PaginatedResponse<User> getAllUsers(String search, String sortBy, String sortOrder, int pageNo, int pageSize) {
        Sort sort = Sort.by("id").descending();
        if(!sortBy.isEmpty()){
            if (!sortOrder.isEmpty()) {
                sort = sortOrder.equals("asc")  ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            } else {
                sort = Sort.by(sortBy).ascending();
            }
        }
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        return new PaginatedResponse<>(userRepository.findAll(pageRequest));  // Fetch all users from the database
    }

    public User getUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password before saving
        if (user.getRole() == null) user.setRole(Role.USER); // Set default role if not provided
        userRepository.save(user);  // Save the user
    }
}