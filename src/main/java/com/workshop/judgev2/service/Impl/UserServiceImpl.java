package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.RoleName;
import com.workshop.judgev2.model.entity.User;
import com.workshop.judgev2.model.service.UserServiceModel;
import com.workshop.judgev2.model.view.UserProfileViewModel;
import com.workshop.judgev2.repository.UserRepository;
import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.RoleService;
import com.workshop.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        if (this.userRepository.count() == 0) {
            user.setRole(this.roleService.getRole(RoleName.ADMIN));
        } else {
            user.setRole(this.roleService.getRole(RoleName.USER));
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId());
        currentUser.setUsername(userServiceModel.getUsername());
        currentUser.setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        this.currentUser.setId(0);
        this.currentUser.setUsername(null);
        this.currentUser.setRole(null);
    }

    @Override
    public List<String> getAllUsernames() {
        return this.userRepository.findAllByUsername();
    }

    @Override
    public void changeRole(String username, RoleName roleName) {

        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.get().getRole().getName() != roleName) {
            user.get().setRole(this.roleService.getRole(roleName));
            this.userRepository.save(user.get());
        }
    }

    @Override
    public User getById(long id) {

        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such user"));
    }

    @Override
    public UserProfileViewModel findProfileById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
            UserProfileViewModel userProfileViewModel = modelMapper.map(user, UserProfileViewModel.class);
            userProfileViewModel.setHomeworks(user.getHomeworks().stream().map(homework -> homework.getExercise().getName()).collect(Collectors.toList()));
            return userProfileViewModel;
        }).orElse(null);
    }

    @Override
    public Long getUsersCount() {
        return userRepository.count();
    }
}
