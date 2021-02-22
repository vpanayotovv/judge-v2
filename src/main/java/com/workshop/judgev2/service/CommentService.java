package com.workshop.judgev2.service;

import com.workshop.judgev2.model.binding.CommentBindingModel;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void addComment(CommentBindingModel commentBindingModel);
}
