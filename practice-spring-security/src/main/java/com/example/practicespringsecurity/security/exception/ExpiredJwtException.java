package com.example.practicespringsecurity.security.exception;


public class ExpiredJwtException extends CustomException {

  public static final CustomException EXCEPTION =
      new ExpiredJwtException();

  private ExpiredJwtException() {
    super(ErrorCode.EXPIRED_TOKEN);
  }
}
