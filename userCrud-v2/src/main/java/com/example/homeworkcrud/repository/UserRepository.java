package com.example.homeworkcrud.repository;

import com.example.homeworkcrud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Integer> {

}
