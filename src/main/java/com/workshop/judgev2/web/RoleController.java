package com.workshop.judgev2.web;

import com.workshop.judgev2.model.entity.RoleName;
import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public RoleController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String add(Model model){

        if (currentUser.isAnonymous()){
            return "redirect:/users/login";
        }

        model.addAttribute("names",this.userService.getAllUsernames());

        return "role-add";
    }

    @PostMapping("/add")
    public String addConfirm(@RequestParam String username,
                             @RequestParam String role){


        this.userService.changeRole(username, RoleName.valueOf(role.toUpperCase()));

        return "redirect:/";
    }
}
