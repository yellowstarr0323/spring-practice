package com.example.homeworkcrud.controller;

import com.example.homeworkcrud.domain.Comment;
import com.example.homeworkcrud.domain.User;
import com.example.homeworkcrud.service.CommentService;
import com.example.homeworkcrud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
  private final CommentService commentService;
  private final UserService userService;

  public CommentController(CommentService commentService, UserService userService) {
    this.commentService = commentService;
    this.userService = userService;
  }

  @GetMapping("/comments/new")
  public String createForm(Model model) {
    model.addAttribute("comment", new Comment());
    model.addAttribute("user", userService.findAll());
    return "comments/createComment";
  }

  @PostMapping("/comments/new")
  public String createComment(@RequestParam String content, @RequestParam Integer userId) {
    User user =userService.findById(userId);

    if(user == null) {
      return "redirect:/";
    }
    Comment comment = new Comment();
    comment.setUser(user);
    comment.setComment(content);
    commentService.save(comment);
    return "redirect:/";
  }
  @GetMapping("/comments/list")
  public String listComments(Model model) {
    model.addAttribute("comments", commentService.findAll());
    return "comments/commentList";
  }

  @GetMapping("/comments/update/{id}")
  public String commentUpdateForm(@PathVariable Integer id, Model model) {
    Comment comment = commentService.findById(id);

    CommentForm form = new CommentForm();
    form.setId(comment.getId());
    form.setComment(comment.getComment());
    model.addAttribute("userId",comment.getUser().getId());
    model.addAttribute("commentForm", form);
    model.addAttribute("userName", comment.getUser().getName());

    return "comments/commentUpdate";
  }

  @PostMapping("/comments/update")
  public String commentUpdate(@ModelAttribute("commentForm") Comment commentForm) {
    Comment comment = commentService.findById(commentForm.getId());
    if(comment == null) {
      return "redirect:/";
    }
    comment.setComment(commentForm.getComment());
    commentService.save(comment);
    return "redirect:/comments/list";

  }
  @GetMapping ("/comments/delete")
  public String showDeleteComment() {
    return "comments/commentDelete";
  }

  @PostMapping("/comments/delete")
  public String deleteComment(@RequestParam Integer id) {
    Comment comment = commentService.findById(id);
    if(comment != null) {
      commentService.delete(comment);
    }
    return "redirect:/comments/list";
  }

}
