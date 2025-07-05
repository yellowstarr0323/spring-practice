package com.example.loginpage.service;

import com.example.loginpage.dto.JoinDTO;
import com.example.loginpage.entity.UserEntity;
import com.example.loginpage.repository.UserRepository;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

  private UserRepository userRepository;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder ) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
  }





  public void joinProcess(JoinDTO joinDTO) {
    boolean isUser =userRepository.existsByUsername(joinDTO.getUsername());//중복검사
    if(isUser){
      return;
    }
    UserEntity userEntity = new UserEntity();

    userEntity.setUsername(joinDTO.getUsername());
    userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
    userEntity.setRole("ROLE_ADMIN");

    userRepository.save(userEntity);
  }
}
