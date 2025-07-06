package com.example.practicespringsecurity.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.access-exp}")
  private Long accessExp;

  @Value("${jwt.refresh-exp}")
  private Long refreshExp;

  private final String header = "Authorization";
  private final String prefix = "Bearer";
}