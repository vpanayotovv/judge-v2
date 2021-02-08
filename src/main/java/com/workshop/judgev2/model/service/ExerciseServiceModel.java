package com.workshop.judgev2.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseServiceModel {

    private Long id;

    private String name;

    private LocalDateTime startedOn;

    private LocalDateTime dueDate;
}
