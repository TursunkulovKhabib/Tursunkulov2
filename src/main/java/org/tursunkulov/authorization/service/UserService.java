package org.tursunkulov.authorization.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

  @Cacheable("users")
  public Optional<List<User>> allUsers() {
    return Optional.ofNullable(UserRepository.getUsers());
  }

  @CachePut(value = "users", key = "#userId.toInt()")
  public Optional<String> getUsername(int id) {
    return Optional.ofNullable(UserRepository.findUserById(id));
  }

  @CacheEvict(value = "users", key = "#userId.toInt()")
  public void deleteUserById(int id) {
    UserRepository.deleteById(id);
  }

  @CacheEvict(value = "username", key = "#username.toString()")
  public void deleteUserByUsername(String username) {
    UserRepository.deleteByUsername(username);
  }

  @CachePut(value = "users", key = "#userId.toInt()")
  public User patchPhoneNumber(int id, String phoneNumber) {
    return UserRepository.patchPhoneNumber(id, phoneNumber);
  }

  @CachePut(value = "users", key = "#userId.toInt()")
  public User patchEmail(int id, String email) {
    return UserRepository.patchEmail(id, email);
  }

  @CachePut(value = "user", key = "#userId.toInt()")
  public void updateUserById(int id, User user) {
    UserRepository.updateUserById(id, user);
  }

  @CacheEvict(value = "user", allEntries = true)
  public void updateUserByUsername(String username, User user) {
    UserRepository.updateUserByUsername(username, user);
  }
}
