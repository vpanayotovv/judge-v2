package com.workshop.judgev2.web;

import com.workshop.judgev2.model.binding.CommentBindingModel;
import com.workshop.judgev2.service.CommentService;
import com.workshop.judgev2.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final HomeworkService homeworkService;
    private final CommentService commentService;

    @Autowired
    public CommentController(HomeworkService homeworkService, CommentService commentService) {
        this.homeworkService = homeworkService;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String add(Model model){

        if(!model.containsAttribute("commentBindingModel")){
            model.addAttribute("commentBindingModel",new CommentBindingModel());
        }
        model.addAttribute("homework",homeworkService.findHomework());
        return "homework-check";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid CommentBindingModel commentBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentBindingModel",commentBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentBindingModel",bindingResult);

            return "redirect:add";
        }

        commentService.addComment(commentBindingModel);

        return "redirect:/";
    }
}
