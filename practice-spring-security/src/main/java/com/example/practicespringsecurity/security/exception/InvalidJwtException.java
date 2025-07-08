package com.example.practicespringsecurity.security.exception;


public class InvalidJwtException extends CustomException {

  public static final CustomException EXCEPTION = new InvalidJwtException();

  private InvalidJwtException() {
    super(ErrorCode.INVALID_TOKEN);
  }
}