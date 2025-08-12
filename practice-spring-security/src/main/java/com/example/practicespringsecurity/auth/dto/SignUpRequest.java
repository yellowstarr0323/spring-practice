package com.example.practicespringsecurity.auth.dto;

import com.example.practicespringsecurity.auth.Role;
import lombok.Getter;

@Getter
public class SignUpRequest {

  private Long id;

  private String accountId;

  private String username;

  private String password;

  private Role role;

  private int age;

}
