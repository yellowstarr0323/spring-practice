package com.example.practicespringsecurity.security.jwt;


import com.example.practicespringsecurity.security.exception.ExpiredJwtException;
import com.example.practicespringsecurity.security.exception.InvalidJwtException;
import com.example.practicespringsecurity.service.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider{

  private final JwtProperties jwtProperties;
  private final AuthDetailsService authDetailsService;

  private static final String ACCESS_KEY = "access_token";

  public String createAccessToken(String accountId) {
    return createToken(accountId, ACCESS_KEY, jwtProperties.getAccessExp() * 10L);
  }

  //JWT 토큰을 직접적으로 생성하는 코드
  private String createToken(String accountId, String type, Long time) {
    Date now = new Date();
    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
        .setSubject(accountId)
        .setHeaderParam("typ", type)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + time))
        .compact();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    return parseToken(bearer);
  }
  //이거 잘 이해 안됨
  public String parseToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.replace("Bearer ", "");
    }
    return null;
  }
  //이쪽 파트 이해 필요
  public UsernamePasswordAuthenticationToken authorization(String token) {
    UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());//2번째 파라미터 뭐임?
  }

  private String getTokenSubject(String subject) {
    return getTokenBody(subject).getSubject();
  }

  private Claims getTokenBody(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(jwtProperties.getSecretKey())
          .parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      throw ExpiredJwtException.EXCEPTION;
    } catch (Exception e) {
      log.error("JWT parse error: {}", e.getMessage(), e);
      throw InvalidJwtException.EXCEPTION;
    }
  }
}