package com.Satellite.service;

import com.Satellite.config.JwtProvider;
import com.Satellite.model.Student;
import com.Satellite.model.Teacher;
import com.Satellite.model.User;
import com.Satellite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String username=jwtProvider.getUsernameFromJwtToken(jwt);
        User user=findUserByUsername(username);
        return user;

    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        User user =userRepository.findByUsername(username);
        if(user ==null)
        {
            throw new Exception("user not found");
        }
       return user;

    }



}
