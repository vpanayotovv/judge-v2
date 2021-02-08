package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.Homework;
import com.workshop.judgev2.repository.HomeworkRepository;
import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.ExerciseService;
import com.workshop.judgev2.service.HomeworkService;
import com.workshop.judgev2.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public void addHomework(String name, String gitAddress) {
        Homework homework = new Homework();
        homework.setGitAddress(gitAddress);
        homework.setAddedOn(LocalDateTime.now());
        homework.setExercise(exerciseService.getByName(name));
        homework.setAuthor(this.userService.getById(currentUser.getId()));
        this.homeworkRepository.saveAndFlush(homework);
    }
}
