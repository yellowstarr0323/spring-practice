package com.example.tempjson.service;

import com.example.tempjson.controller.CommentFormRequest;
import com.example.tempjson.domain.Comment;
import com.example.tempjson.domain.User;
import com.example.tempjson.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final UserService userService;

  public CommentService(CommentRepository commentRepository , UserService userService) {
    this.commentRepository = commentRepository;
    this.userService = userService;
  }
  public void save( Integer id,CommentFormRequest commentFormRequest) {
    Comment comment = new Comment();
    User user= userService.findById(id);
    comment.setUser(user);
    comment.setContent(commentFormRequest.getContent());
    commentRepository.save(comment);
  }

  public void update(Integer id,CommentFormRequest commentFormRequest) {
    Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Comment not found"));
    comment.setContent(commentFormRequest.getContent());
    commentRepository.save(comment);
  }
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  public Comment findById(Integer id) {
    return commentRepository.findById(id).get();
  }

  public void deleteById(Integer id) {
    commentRepository.deleteById(id);
  }
}
