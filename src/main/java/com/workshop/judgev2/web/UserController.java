package com.workshop.judgev2.web;

import com.workshop.judgev2.model.binding.UserRegisterBindingModel;
import com.workshop.judgev2.model.service.UserServiceModel;
import com.workshop.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel,UserServiceModel.class);

        this.userService.registerUser(userServiceModel);

        return "redirect:register";
    }

}
