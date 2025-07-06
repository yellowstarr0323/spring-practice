package com.example.practicespringsecurity.security.exception;

public class UserNotFoundException extends RuntimeException{

  public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

  public UserNotFoundException() {

    super(EXCEPTION);
  }
}
