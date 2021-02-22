package com.workshop.judgev2.service;

import com.workshop.judgev2.model.entity.RoleName;
import com.workshop.judgev2.model.entity.User;
import com.workshop.judgev2.model.service.UserServiceModel;
import com.workshop.judgev2.model.view.UserProfileViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> getAllUsernames();

    void changeRole(String username, RoleName roleName);

    User getById(long id);

    UserProfileViewModel findProfileById(Long id);

    Long getUsersCount();
}
