package com.example.homeworkcrud.service;

import com.example.homeworkcrud.domain.Comment;
import com.example.homeworkcrud.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void save(Comment comment) {
    commentRepository.save(comment);
  }
  public Comment findById(int id) {
    return commentRepository.findById(id).get();
  }
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }
  public void delete(Comment comment) {
    commentRepository.delete(comment);
  }
}