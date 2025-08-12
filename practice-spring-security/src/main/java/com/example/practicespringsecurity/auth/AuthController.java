package com.example.practicespringsecurity.auth;

import com.example.practicespringsecurity.auth.dto.LoginRequest;
import com.example.practicespringsecurity.auth.dto.TokenResponse;
import com.example.practicespringsecurity.auth.dto.SignUpRequest;
import com.example.practicespringsecurity.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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
