package com.onefineday.manage.services;

import com.onefineday.manage.models.User;
import com.onefineday.manage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();  // Fetch all users from the database
    }
}