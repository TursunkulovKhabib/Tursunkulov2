package org.tursunkulov.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/login")
public class AuthController implements AuthControllerApi {

  @Override
  @PostMapping("/registration")
  public ResponseEntity<String> registration(
      @RequestParam int id,
      @RequestParam String username,
      @RequestParam String password,
      @RequestParam String email,
      @RequestParam String phoneNumber) {
    User user = new User(id, username, password, email, phoneNumber);
    log.debug("Регистрация пользователя");
    return ResponseEntity.ok(AuthService.registration(user));
  }

  @Override
  @PostMapping("/authorization")
  public ResponseEntity<String> authentication(
      @RequestParam String username, @RequestParam String password) {
    log.debug("Аутентификация пользователя");
    return ResponseEntity.ok(AuthService.checkUser(username, password));
  }
}
