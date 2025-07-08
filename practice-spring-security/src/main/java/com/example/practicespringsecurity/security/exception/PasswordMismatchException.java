package com.example.practicespringsecurity.security.exception;

public class PasswordMismatchException extends RuntimeException {
  public static final PasswordMismatchException EXCEPTION = new PasswordMismatchException();
  public PasswordMismatchException() {
    super("Password mismatch");
  }

}
