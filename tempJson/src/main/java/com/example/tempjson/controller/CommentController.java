package com.example.tempjson.controller;

import com.example.tempjson.domain.Comment;
import com.example.tempjson.service.CommentService;
import com.example.tempjson.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

  private final CommentService commentService;
  public CommentController(CommentService commentService) {

    this.commentService = commentService;
  }

  @PostMapping("/comment-create")
  public void createComment(@RequestParam Integer userId, @RequestBody CommentFormRequest commentFormRequest) {
    commentService.save(userId, commentFormRequest);
  }

  @GetMapping("/comment-list")
  public List<Comment> commentList() {
    return commentService.findAll();
  }

  @PutMapping("/comment-update/{id}")
  public void updateComment(@PathVariable Integer id,@RequestBody CommentFormRequest commentFormRequest) {
    commentService.update(id, commentFormRequest);
  }

  @DeleteMapping("/comment-delete/{id}")
  public void deleteComment(@PathVariable Integer id) {
    commentService.deleteById(id);
  }
}
