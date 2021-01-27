package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.RoleName;
import com.workshop.judgev2.model.entity.User;
import com.workshop.judgev2.model.service.UserServiceModel;
import com.workshop.judgev2.repository.UserRepository;
import com.workshop.judgev2.service.RoleService;
import com.workshop.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        user.setRole(this.roleService.getRole(RoleName.USER));
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
       return this.userRepository.findByUsername(username)
               .map(user -> this.modelMapper.map(user,UserServiceModel.class))
               .orElseThrow( () -> new IllegalArgumentException("Username not found!"));
    }
}
