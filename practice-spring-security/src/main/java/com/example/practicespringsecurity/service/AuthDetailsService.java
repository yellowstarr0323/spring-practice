package com.example.practicespringsecurity.service;



import com.example.practicespringsecurity.domain.AuthDetails;
import com.example.practicespringsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.practicespringsecurity.security.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetails loadUserByUsername(String accountId) {
    return new AuthDetails(userRepository.findByAccountId(accountId)
        .orElseThrow(() -> {
          return UserNotFoundException.EXCEPTION;
        }));
  }
}