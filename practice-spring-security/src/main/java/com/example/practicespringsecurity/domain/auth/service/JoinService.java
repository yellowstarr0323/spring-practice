package com.example.practicespringsecurity.domain.auth.service;

import com.example.practicespringsecurity.domain.user.controller.dto.UserDto;
import com.example.practicespringsecurity.domain.user.domain.User;
import com.example.practicespringsecurity.domain.user.domain.repository.UserRepository;
import com.example.practicespringsecurity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User join(UserDto userDto) {

    if(userRepository.existsByAccountId(userDto.getAccountId())) {
      throw new IllegalArgumentException("이미 존재하는 계정입니다");
    }

    User user= User.builder()
        .id(userDto.getId())
        .accountId(userDto.getAccountId())
        .username(userDto.getUsername())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .role(userDto.getRole())
        .build();

    return userRepository.save(user);

  }
}
