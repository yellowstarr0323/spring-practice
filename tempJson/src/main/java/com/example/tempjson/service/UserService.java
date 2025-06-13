package com.example.tempjson.service;

import com.example.tempjson.controller.UserFormRequest;
import com.example.tempjson.domain.User;
import com.example.tempjson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User userSave(UserFormRequest userFormRequest) {
    User user = new User(userFormRequest.getName(),userFormRequest.getAge());
    return userRepository.save(user);
  }

  public User userUpdate(Integer id,UserFormRequest userFormRequest) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setName(userFormRequest.getName());
    user.setAge(userFormRequest.getAge());
    return userRepository.save(user);

  }
  public User findById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }
}
