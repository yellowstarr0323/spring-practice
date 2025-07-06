package com.example.practicespringsecurity.security.exception;

import com.example.practicespringsecurity.security.jwt.exception.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  EXPIRED_TOKEN(HttpStatus.FORBIDDEN),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED);

  private final HttpStatus httpStatus;


}
