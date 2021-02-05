package com.workshop.judgev2.web;

import com.workshop.judgev2.model.binding.ExerciseAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController  {

    @GetMapping("/add")
    public String addExercise(Model model){
        if (!model.containsAttribute("exerciseAddBindingModel")){
            model.addAttribute("exerciseAddBindingModel" , new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ExerciseAddBindingModel exerciseAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel" ,exerciseAddBindingModel);
            return "redirect:add";
        }

        return "redirect:/";
    }
}
