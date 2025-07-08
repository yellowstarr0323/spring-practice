package com.example.practicespringsecurity.domain;

import jakarta.persistence.*;
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

  private int age;


  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public User(Long id,String accountId,String username, String password, Role role, int age) {
    this.id=id;
    this.age=age;
    this.accountId = accountId;
    this.username = username;
    this.password = password;
    this.role = role;

  }

}
