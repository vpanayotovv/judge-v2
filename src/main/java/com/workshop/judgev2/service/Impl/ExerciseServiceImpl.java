package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.Exercise;
import com.workshop.judgev2.model.service.ExerciseServiceModel;
import com.workshop.judgev2.repository.ExerciseRepository;
import com.workshop.judgev2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<String> getAllExercise() {

        return this.exerciseRepository.getAllExerices();
    }

    @Override
    public boolean isLate(String exercise) {

        Exercise exerciseEntity = this.exerciseRepository.findByName(exercise).orElseThrow(() -> new IllegalArgumentException("exercise do not exist"));

        return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public Exercise getByName(String name) {
        return this.exerciseRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("exercise do not exist"));
    }
}
