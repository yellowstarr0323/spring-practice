package com.example.homeworkcrud.controller;

import com.example.homeworkcrud.domain.User;
import com.example.homeworkcrud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("users/new")
  public String createForm() {

    return "users/createUser";
  }
  //create
  @PostMapping("users/new")
  public String createUser(UserForm userForm) {
    User user = new User();
    user.setName(userForm.getName());
    user.setAge(userForm.getAge());
    System.out.println("member.getName(): " + user.getName());
    System.out.println("member.getAge(): " + user.getAge());
    userService.save(user);
    return  "redirect:/";
  }
  //Read
  @GetMapping("users/list")
  public String userList(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "users/userList";
  }
  //Update
  @GetMapping("/users/update/{id}")
  public String showUpdateForm(@PathVariable Integer id, Model model) {
    User user = userService.findById(id);

    UserForm form = new UserForm();
    form.setId(user.getId());
    form.setName(user.getName());
    form.setAge(user.getAge());
    model.addAttribute("userForm", form);
    return "users/userUpdate";
  }

  @PostMapping("/users/update")
  public String updateUser(@ModelAttribute("userForm") UserForm userForm) {
    User user = userService.findById(userForm.getId());
    if(user==null) {
      return "redirect:/";
    }
    user.setName(userForm.getName());
    user.setAge(userForm.getAge());
    userService.save(user);
    return "redirect:/users/list";
  }

  @GetMapping("/users/delete")
  public String showDeleteForm() {
      return "users/userDelete";
    }

  @PostMapping("/users/delete")
  public String deleteUser(@RequestParam Integer id) {
    User user=userService.findById(id);
    if (user != null) {
      userService.delete(user);
    }
    return "redirect:/users/list";
  }
  }

