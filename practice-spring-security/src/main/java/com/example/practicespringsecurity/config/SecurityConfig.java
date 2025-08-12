package com.example.practicespringsecurity.config;

import com.example.practicespringsecurity.security.jwt.JwtTokenFilter;
import com.example.practicespringsecurity.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class  SecurityConfig {
  private final JwtTokenProvider jwtProvider;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //보안 기능 비활성화
    http.csrf(csrf -> csrf.disable())
        .httpBasic(httpBasic -> httpBasic.disable())
        .formLogin(formLogin -> formLogin.disable())
        .logout(logout -> logout.disable());

    //세션을 생성하지 않음
    http.sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    //인증 실패시 401 UNAUTHORIZED 코드를 날림
    http.exceptionHandling(exception -> exception
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

    // jwt 인증 필터를 UsernamePasswordAuthenticationFilter(기본 인증 필터)보다 먼저 실행
    http.addFilterBefore(new JwtTokenFilter(jwtProvider),
        UsernamePasswordAuthenticationFilter.class);


    http.cors(cor -> {
    });

    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/admin").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/sign-up").permitAll()
        .requestMatchers("/user").hasRole("USER")
        .requestMatchers("/**").permitAll());




    return http.build();
  }
}