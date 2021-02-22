package com.workshop.judgev2.web;

import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;

    public HomeController(ExerciseService exerciseService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(Model model){
        if (currentUser.isAnonymous()){
            return "index";
        }

        model.addAttribute("exercises",exerciseService.getAllExercise());
        return "home";
    }

}
