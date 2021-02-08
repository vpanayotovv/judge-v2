package com.workshop.judgev2.service;

import com.workshop.judgev2.model.entity.Exercise;
import com.workshop.judgev2.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addEx(ExerciseServiceModel exerciseServiceModel);

    List<String> getAllExercise();

    boolean isLate(String exercise);

    Exercise getByName(String name);
}
