package com.example.practicespringsecurity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

  @Value
  private String redisHost;

  @Value
  private String redisPort;

  @Value
  private String password;

  
}
