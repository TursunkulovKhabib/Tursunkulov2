package org.tursunkulov.authorization.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tursunkulov.authorization.entity.User;
import org.tursunkulov.authorization.service.UserService;

@Slf4j
@AllArgsConstructor
@Controller
@RestController
@RequestMapping("/user")
@RateLimiter(name = "apiRateLimiter")
@CircuitBreaker(name = "apiCircuitBreaker")
public class UserController implements UserControllerApi {

  private final UserService userService;

  @Override
  @GetMapping("/info")
  public ResponseEntity<Optional<List<User>>> info() {
    log.info("Получение списка всех пользователей");
    return ResponseEntity.ok(userService.allUsers());
  }

  @Override
  @GetMapping("/username/{id}")
  public ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
    log.info("Получения имени пользователя по id");
    return ResponseEntity.ok(userService.findUserById(id));
  }

  @Override
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable int id) {
    userService.deleteUserById(id);
    log.info("Удаления пользователя по id");
    return ResponseEntity.noContent().build();
  }

  @Override
  @DeleteMapping("/delete/{username}")
  public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
    userService.deleteUserByUsername(username);
    log.info("Удаления пользователя по username");
    return ResponseEntity.noContent().build();
  }

  @Override
  @PatchMapping("/patchPhoneNumber/{id}")
  public ResponseEntity<Optional<User>> patchPhoneNumber(
      @PathVariable int id, @PathVariable String phoneNumber) {
    log.info("Смена номера телефона");
    return ResponseEntity.ok(userService.patchPhoneNumber(id, phoneNumber));
  }

  @Override
  @PatchMapping("/patchEmail/{id}")
  public ResponseEntity<Optional<User>> patchEmail(
      @PathVariable int id, @PathVariable String email) {
    log.info("Смена электронной почты");
    return ResponseEntity.ok(userService.patchEmail(id, email));
  }

  @Override
  @PutMapping("/updateUserById/{id}")
  public ResponseEntity<Void> updateUserById(@PathVariable int id, @RequestBody User user) {
    userService.updateUserById(id, user);
    log.info("Обновить пользователя по id");
    return ResponseEntity.noContent().build();
  }

  @Override
  @PutMapping("/updateUserByUsername/{id}")
  public ResponseEntity<Void> updateUserByUsername(
      @PathVariable String username, @RequestBody User user) {
    userService.updateUserByUsername(username, user);
    log.info("Обновить пользователя по username: {}", username);
    return ResponseEntity.noContent().build();
  }
}
