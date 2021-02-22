package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.binding.CommentBindingModel;
import com.workshop.judgev2.model.entity.Comment;
import com.workshop.judgev2.repository.CommentRepository;
import com.workshop.judgev2.security.CurrentUser;
import com.workshop.judgev2.service.CommentService;
import com.workshop.judgev2.service.HomeworkService;
import com.workshop.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final HomeworkService homeworkService;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, HomeworkService homeworkService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.homeworkService = homeworkService;
    }

    @Override
    public void addComment(CommentBindingModel commentBindingModel) {
        Comment comment = modelMapper.map(commentBindingModel,Comment.class);
        comment.setAuthor(userService.getById(currentUser.getId()));
        comment.setHomework(homeworkService.findById(commentBindingModel.getHomeworkId()));
        commentRepository.save(comment);
    }

    @Override
    public Double findAvgScore() {
        return commentRepository.findAveragedScore();
    }

    @Override
    public Map<Integer, Integer> findScore() {
        Map<Integer,Integer> result = initScore();
        commentRepository
                .findAll()
                .forEach(comment -> {
                    Integer score = comment.getScore();
                    result.put(score,result.get(score) + 1);
                });
        return result;
    }

    private Map<Integer, Integer> initScore() {
        Map<Integer,Integer> result = new HashMap<>();
        for (int i = 2; i <= 6; i++) {
            result.put(i,0);
        }
        return result;
    }
}
