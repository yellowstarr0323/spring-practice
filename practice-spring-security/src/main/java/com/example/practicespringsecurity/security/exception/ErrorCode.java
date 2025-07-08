package com.example.practicespringsecurity.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  EXPIRED_TOKEN(HttpStatus.FORBIDDEN),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED);

  private final HttpStatus httpStatus;


}
