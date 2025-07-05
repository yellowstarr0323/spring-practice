package com.example.practicespringsecurity.domain.auth.controller;

import com.example.practicespringsecurity.domain.auth.service.JoinService;
import com.example.practicespringsecurity.domain.user.controller.dto.UserDto;
import com.example.practicespringsecurity.domain.user.domain.repository.UserRepository;

import com.example.practicespringsecurity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

  private final JoinService joinService;

  @PostMapping()
  public User signUp(@RequestBody UserDto userDto) {
    joinService.join(userDto);


  }
}
