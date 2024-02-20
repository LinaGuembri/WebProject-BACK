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



    // Method to update an existing user
    public User updateUser(Long id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFname(newUser.getFname());
            existingUser.setLname(newUser.getLname());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setTelephone(newUser.getTelephone());
            existingUser.setPwd(newUser.getPwd());
            existingUser.setRole(newUser.getRole());
            return userRepository.save(existingUser);
        } else {
            // Handle error if user not found
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Method to delete a user from the database
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
