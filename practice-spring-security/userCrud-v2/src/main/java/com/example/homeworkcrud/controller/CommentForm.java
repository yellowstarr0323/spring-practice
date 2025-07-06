package com.example.homeworkcrud.controller;

public class CommentForm {
  private String comment;
  private int id;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
