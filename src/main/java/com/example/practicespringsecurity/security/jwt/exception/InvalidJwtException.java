package com.example.practicespringsecurity.security.jwt.exception;

import com.example.practicespringsecurity.security.exception.CustomException;
import com.example.practicespringsecurity.security.exception.ErrorCode;


public class InvalidJwtException extends CustomException {

  public static final CustomException EXCEPTION = new InvalidJwtException();

  private InvalidJwtException() {
    super(ErrorCode.INVALID_TOKEN);
  }
}