package com.example.tempjson.controller;

import com.example.tempjson.domain.User;
import com.example.tempjson.service.UserService;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user-create")
  @ResponseStatus(code = HttpStatus.CREATED)
  public User createUser(@RequestBody UserFormRequest userFormRequest) {
    return userService.userSave(userFormRequest);
  }

  @GetMapping("/user-list")
  public List<User> userList() {
    return userService.findAll();
  }

  @PutMapping("/user-update/{id}")
  public User userUpdate(@PathVariable Integer id, @RequestBody UserFormRequest userFormRequest) {
    return userService.userUpdate(id,userFormRequest);
  }

  @DeleteMapping("/user-delete/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void userDelete(@PathVariable Integer id) {
    userService.deleteById(id);
  }
}
