package com.Satellite.service;

import com.Satellite.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByUsername(String username)throws Exception;

}
