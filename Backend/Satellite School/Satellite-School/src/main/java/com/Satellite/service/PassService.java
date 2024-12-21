package com.Satellite.service;

import com.Satellite.model.User;

public interface PassService {
    public boolean changePassword(User user, String oldPassword, String newPassword);
}
