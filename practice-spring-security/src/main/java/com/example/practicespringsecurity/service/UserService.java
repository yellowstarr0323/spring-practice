package com.example.practicespringsecurity.service;

import com.example.practicespringsecurity.dto.SignUpRequest;
import com.example.practicespringsecurity.domain.Role;
import com.example.practicespringsecurity.domain.User;
import com.example.practicespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;



  public List<User> userList() {
    return userRepository.findAll();
  }
}
