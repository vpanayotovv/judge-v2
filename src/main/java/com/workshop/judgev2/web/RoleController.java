package com.workshop.judgev2.web;

import com.workshop.judgev2.model.entity.RoleName;
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

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(Model model){

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
