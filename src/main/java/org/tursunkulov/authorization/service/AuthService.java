package org.tursunkulov.authorization.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.AuthRepository;

@Service
public class AuthService {

  @CacheEvict(value = "user", allEntries = true)
  public String registration(User user) {
    return AuthRepository.saveUser(user);
  }

  @CachePut(value = "username", key = "#username.toString()")
  public String checkUser(String username, String password) {
    return AuthRepository.checkUser(username, password);
  }
}
