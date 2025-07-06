package com.example.homeworkcrud.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String comment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
