package com.ea.project.respository;

import com.ea.project.util.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TokenRepository {
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  private Map<String, Token> tokens = new HashMap<>();
  public Token findToken(String token) {
    return tokens.get(token);
  };

  public boolean isExpired(String token) {
    Token t = findToken(token);
    if(t != null) {
      return ((System.currentTimeMillis() - t.issued) > jwtExpiration);
    }
    return true;
  }

  public void removeToken(String token) {
    tokens.remove(token);
  }

  public void saveToken(Token token) {
    tokens.put(token.token, token);
  }

}
