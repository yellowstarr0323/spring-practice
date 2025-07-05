package com.example.practicespringsecurity.domain.user.controller.dto;

import com.example.practicespringsecurity.domain.user.domain.Role;
import lombok.Getter;

@Getter
public class UserDto {
  private Long id;
  private String accountId;
  private String username;
  private String password;
  private Role role;

}
