package com.example.practicespringsecurity.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

  private String accountId;
  private String password;

}
