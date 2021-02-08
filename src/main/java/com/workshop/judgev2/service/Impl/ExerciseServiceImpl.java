package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.Exercise;
import com.workshop.judgev2.model.service.ExerciseServiceModel;
import com.workshop.judgev2.repository.ExerciseRepository;
import com.workshop.judgev2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addEx(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = this.modelMapper.map(exerciseServiceModel,Exercise.class);
        this.exerciseRepository.saveAndFlush(exercise);
    }
}
