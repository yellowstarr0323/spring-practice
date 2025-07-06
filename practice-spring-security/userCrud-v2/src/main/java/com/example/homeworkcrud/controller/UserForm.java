package com.example.homeworkcrud.controller;

import org.aspectj.weaver.loadtime.Agent;

import javax.naming.Name;

public class UserForm {
  private int id;
  private int age;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
