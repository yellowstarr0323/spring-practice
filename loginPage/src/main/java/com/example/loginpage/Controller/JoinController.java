package com.example.loginpage.Controller;

import com.example.loginpage.dto.JoinDTO;
import com.example.loginpage.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

  private final JoinService joinService;
  public JoinController(JoinService joinService) {
    this.joinService = joinService;
  }

  @GetMapping("/join")
  public String joinP() {
    return "join";

  }

  @PostMapping("/joinProc")
  public String StringProcess(JoinDTO joinDTO){
    System.out.println(joinDTO.getUsername());
    joinService.joinProcess(joinDTO);
    return "redirect:/loginPage";
  }
}
