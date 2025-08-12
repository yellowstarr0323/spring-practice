package com.example.practicespringsecurity.auth;

import com.example.practicespringsecurity.user.User;
import com.example.practicespringsecurity.auth.dto.LoginRequest;
import com.example.practicespringsecurity.auth.dto.SignUpRequest;
import com.example.practicespringsecurity.auth.dto.TokenResponse;
import com.example.practicespringsecurity.user.UserRepository;
import com.example.practicespringsecurity.security.exception.PasswordMismatchException;
import com.example.practicespringsecurity.security.exception.UserNotFoundException;
import com.example.practicespringsecurity.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public User join(SignUpRequest userDto) {

    if(userRepository.existsByAccountId(userDto.getAccountId())) {
      throw new IllegalArgumentException("이미 존재하는 계정입니다");
    }

    User user= User.builder()
        .id(userDto.getId())
        .accountId(userDto.getAccountId())
        .username(userDto.getUsername())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .role(Role.ROLE_ADMIN)
        .age(userDto.getAge())
        .build();

    return userRepository.save(user);

  }

  public TokenResponse signIn(LoginRequest loginRequest) {
    String accountId = loginRequest.getAccountId();
    String password = loginRequest.getPassword();
    User user = userRepository.findByAccountId(accountId)
        .orElseThrow(() ->UserNotFoundException.EXCEPTION);

    if(!passwordEncoder.matches(password,user.getPassword())){
      throw PasswordMismatchException.EXCEPTION;
    }
    String accessToken=jwtTokenProvider.createAccessToken(accountId);
    return new TokenResponse(accessToken);
  }
}
