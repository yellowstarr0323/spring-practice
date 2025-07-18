package com.example.practicespringsecurity.repository;

import com.example.practicespringsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByAccountId(String accountId);

  public boolean existsByAccountId(String accountId);

}
