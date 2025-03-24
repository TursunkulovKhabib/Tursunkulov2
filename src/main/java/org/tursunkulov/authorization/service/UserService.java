package org.tursunkulov.authorization.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

  public static Optional<List<User>> allUsers() {
    return Optional.ofNullable(UserRepository.getUsers());
  }

  public static Optional<String> getUsername(int id) {
    return Optional.ofNullable(UserRepository.findUserById(id));
  }

  public static void deleteUserById(int id) {
    UserRepository.deleteById(id);
  }

  public static void deleteUserByUsername(String username) {
    UserRepository.deleteByUsername(username);
  }

  public static User patchPhoneNumber(int id, String phoneNumber) {
    return UserRepository.patchPhoneNumber(id, phoneNumber);
  }

  public static User patchEmail(int id, String email) {
    return UserRepository.patchEmail(id, email);
  }

  public static void updateUserById(int id, User user) {
    UserRepository.updateUserById(id, user);
  }

  public static void updateUserByUsername(String username, User user) {
    UserRepository.updateUserByUsername(username, user);
  }
}
