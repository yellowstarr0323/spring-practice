package com.example.practicespringsecurity.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TokenResponse {
  private final String accessToken;

}
