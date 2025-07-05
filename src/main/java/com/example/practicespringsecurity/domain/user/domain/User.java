package com.example.practicespringsecurity.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String username;

  private String password;

  private String accountId;

  private Role role;//아니 시발 개능딸 짓함

  @Builder
  public User(Long id,String accountId,String username, String password, Role role) {
    this.id=id;
    this.accountId = accountId;
    this.username = username;
    this.password = password;
    this.role = role;

  }

}
