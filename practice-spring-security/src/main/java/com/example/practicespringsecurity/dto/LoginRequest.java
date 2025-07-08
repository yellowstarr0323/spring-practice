package com.example.practicespringsecurity.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

  private String accountId;
  private String password;

}
