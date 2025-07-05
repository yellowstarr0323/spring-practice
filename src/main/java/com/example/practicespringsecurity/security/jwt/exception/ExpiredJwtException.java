package com.example.practicespringsecurity.security.jwt.exception;

import com.example.practicespringsecurity.security.exception.CustomException;
import com.example.practicespringsecurity.security.exception.ErrorCode;


public class ExpiredJwtException extends CustomException {

  public static final CustomException EXCEPTION =
      new ExpiredJwtException();

  private ExpiredJwtException() {
    super(ErrorCode.EXPIRED_TOKEN);
  }
}
