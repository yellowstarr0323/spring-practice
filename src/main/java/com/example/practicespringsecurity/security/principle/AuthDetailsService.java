package com.example.practicespringsecurity.security.principle;



import com.example.practicespringsecurity.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.practicespringsecurity.security.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String accountId) {
    return new AuthDetails(userRepository.findByAccountId(accountId)
        .orElseThrow(() -> UserNotFoundException.EXCEPTION));
  }
}