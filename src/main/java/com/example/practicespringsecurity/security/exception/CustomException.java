package com.example.practicespringsecurity.security.exception;

import ch.qos.logback.core.spi.ErrorCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
  private final ErrorCode errorCode;

}
