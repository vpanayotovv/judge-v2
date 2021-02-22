package com.workshop.judgev2.service;

import com.workshop.judgev2.model.entity.Homework;

public interface HomeworkService {
    void addHomework(String name, String gitAddress);

    Homework findHomework();

    Homework findById(Long homeworkId);
}
