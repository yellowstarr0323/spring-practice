package com.example.homeworkcrud.repository;

import com.example.homeworkcrud.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
