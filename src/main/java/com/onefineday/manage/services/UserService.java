package com.onefineday.manage.services;

import com.onefineday.manage.models.Role;
import com.onefineday.manage.models.User;
import com.onefineday.manage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();  // Fetch all users from the database
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