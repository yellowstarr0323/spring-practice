package com.example.practicespringsecurity.security.jwt;


import com.example.practicespringsecurity.config.JwtProperties;
import com.example.practicespringsecurity.security.jwt.exception.ExpiredJwtException;
import com.example.practicespringsecurity.security.jwt.exception.InvalidJwtException;
import com.example.practicespringsecurity.security.principle.AuthDetailsService;
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
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;
  private final AuthDetailsService authDetailsService;

  private static final String ACCESS_KEY = "access_token";

  public String createAccessToken(String accountId) {
    return createToken(accountId, ACCESS_KEY, jwtProperties.getAccessExp() * 10L);
  }

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

  public String parseToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.replace("Bearer ", "");
    }
    return null;
  }

  public UsernamePasswordAuthenticationToken authorization(String token) {
    UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
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