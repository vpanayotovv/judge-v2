package com.workshop.judgev2.service;

import com.workshop.judgev2.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);
}
