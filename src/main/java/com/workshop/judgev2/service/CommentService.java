package com.workshop.judgev2.service;

import com.workshop.judgev2.model.binding.CommentBindingModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommentService {
    void addComment(CommentBindingModel commentBindingModel);

    Double findAvgScore();
    Map<Integer,Integer> findScore();
}
