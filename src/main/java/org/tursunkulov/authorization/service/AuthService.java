package org.tursunkulov.authorization.service;

import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.AuthRepository;

public class AuthService {

  public static String registration(User user) {
    return AuthRepository.saveUser(user);
  }

  public static String checkUser(String username, String password) {
    return AuthRepository.checkUser(username, password);
  }
}
