package com.workshop.judgev2.web;

import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.CommentService;
import com.workshop.judgev2.service.ExerciseService;
import com.workshop.judgev2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public HomeController(ExerciseService exerciseService, CommentService commentService, UserService userService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(Model model){
        if (currentUser.isAnonymous()){
            return "index";
        }

        model.addAttribute("exercises",exerciseService.getAllExercise());
        model.addAttribute("avg",commentService.findAvgScore());
        model.addAttribute("usersCount",userService.getUsersCount());
        model.addAttribute("score",commentService.findScore());
        return "home";
    }

}
