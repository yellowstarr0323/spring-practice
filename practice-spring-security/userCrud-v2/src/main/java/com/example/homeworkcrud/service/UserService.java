package com.example.homeworkcrud.service;

import com.example.homeworkcrud.domain.User;
import com.example.homeworkcrud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }
  public List<User> findAll() {
    return userRepository.findAll();
  }
  public void delete(User user) {

    userRepository.delete(user);
  }
  public User findById(Integer id) {
    return userRepository.findById(id).get();

  }


}
