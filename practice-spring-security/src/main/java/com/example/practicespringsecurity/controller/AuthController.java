package com.example.practicespringsecurity.controller;

import com.example.practicespringsecurity.dto.LoginRequest;
import com.example.practicespringsecurity.dto.TokenResponse;
import com.example.practicespringsecurity.dto.SignUpRequest;
import com.example.practicespringsecurity.security.jwt.JwtTokenProvider;
import com.example.practicespringsecurity.service.AuthService;
import com.example.practicespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthService authService;

  @PostMapping("/sign-up")
  public void signUp(@RequestBody SignUpRequest signUpRequest) {
    authService.join(signUpRequest);

  }

  @PostMapping("/login")
  public TokenResponse signIn(@RequestBody LoginRequest loginRequest) {
    return authService.signIn(loginRequest);
  }

}
