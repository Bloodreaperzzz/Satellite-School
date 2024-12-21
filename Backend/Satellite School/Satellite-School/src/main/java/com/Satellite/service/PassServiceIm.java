package com.Satellite.service;

import com.Satellite.model.User;
import com.Satellite.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PassServiceIm implements PassService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean changePassword(User user, String oldPassword, String newPassword) {




        // Check if the old password matches
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // Encrypt the new password and update the user
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return true; // Indicate success
    }
}
