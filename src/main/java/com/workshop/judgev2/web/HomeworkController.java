package com.workshop.judgev2.web;

import com.workshop.judgev2.model.binding.HomeworkAddBindingModel;
import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.ExerciseService;
import com.workshop.judgev2.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CurrentUser currentUser;

    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String add(Model model){

        if (currentUser.isAnonymous()){
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("homeworkAddBindingModel")){
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate",false);
        }
        model.addAttribute("exercises",this.exerciseService.getAllExercise());
        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid HomeworkAddBindingModel homeworkAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
            return "redirect:add";
        }

        boolean isLate = exerciseService.isLate(homeworkAddBindingModel.getExercise());

        if (isLate){
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel",homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("isLate",true);
        }


        this.homeworkService.addHomework(homeworkAddBindingModel.getExercise(),homeworkAddBindingModel.getGitAddress());

        return "redirect:/";
    }

}
