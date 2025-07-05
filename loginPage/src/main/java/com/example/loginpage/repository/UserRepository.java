package com.example.loginpage.repository;

import com.example.loginpage.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  boolean existsByUsername(String username);


  UserEntity findByUsername(String username);
}
