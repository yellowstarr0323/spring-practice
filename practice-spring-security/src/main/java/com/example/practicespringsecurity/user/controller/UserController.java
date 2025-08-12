package com.example.practicespringsecurity.user.controller;

import com.example.practicespringsecurity.user.UserService;

import com.example.practicespringsecurity.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;


  @GetMapping("/user")
  public String user() {
    return "user";
  }

  @GetMapping("/user-list")
  public List<User> getUserList() {
    return userService.userList();
  }

  @GetMapping("/admin")
  public String admin() {
    System.out.println("admin");
    return "admin";
  }


}
