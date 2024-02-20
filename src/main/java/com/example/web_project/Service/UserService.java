package com.example.web_project.Service;

import com.example.web_project.Entity.User;
import com.example.web_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to save a new user to the database
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Method to retrieve a user by ID from the database
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Method to update an existing user in the database
    public User updateUser(Long id, User newUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFname(newUser.getFname());
            existingUser.setLname(newUser.getLname());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setTelephone(newUser.getTelephone());
            existingUser.setPwd(newUser.getPwd());
            existingUser.setRole(newUser.getRole());
            return userRepository.save(existingUser);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    // Method to delete a user from the database
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
