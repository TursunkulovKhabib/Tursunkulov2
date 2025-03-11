package org.tursunkulov.authorization.controller;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.service.UserService;

@Slf4j
@Controller
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

  @Override
  @GetMapping("/info")
  public ResponseEntity<Optional<List<User>>> info() {
    log.info("Получение списка всех пользователей");
    return ResponseEntity.ok(UserService.allUsers());
  }

  @Override
  @GetMapping("/username/{id}")
  public ResponseEntity<Optional<String>> getUser(@PathVariable int id) {
    log.info("Получения имени пользователя по id");
    return ResponseEntity.ok(UserService.getUsername(id));
  }

  @Override
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable int id) {
    UserService.deleteUserById(id);
    log.info("Удаления пользователя по id");
    return ResponseEntity.noContent().build();
  }

  @Override
  @DeleteMapping("/delete/{username}")
  public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
    UserService.deleteUserByUsername(username);
    log.info("Удаления пользователя по username");
    return ResponseEntity.noContent().build();
  }

  @Override
  @PatchMapping("/patchPhoneNumber/{id}")
  public ResponseEntity<User> patchPhoneNumber(
      @PathVariable int id, @PathVariable String phoneNumber) {
    log.info("Смена номера телефона");
    return ResponseEntity.ok(UserService.patchPhoneNumber(id, phoneNumber));
  }

  @Override
  @PatchMapping("/patchEmail/{id}")
  public ResponseEntity<User> patchEmail(@PathVariable int id, @PathVariable String email) {
    log.info("Смена электронной почты");
    return ResponseEntity.ok(UserService.patchEmail(id, email));
  }

  @Override
  @PutMapping("/updateUserById/{id}")
  public ResponseEntity<Void> updateUserById(@PathVariable int id, @RequestBody User user) {
    UserService.updateUserById(id, user);
    log.info("Обновить пользователя по id");
    return ResponseEntity.noContent().build();
  }

  @Override
  @PutMapping("/updateUserByUsername/{id}")
  public ResponseEntity<Void> updateUserByUsername(
      @PathVariable String username, @RequestBody User user) {
    UserService.updateUserByUsername(username, user);
    log.info("Обновить пользователя по username: {}", username);
    return ResponseEntity.noContent().build();
  }
}
