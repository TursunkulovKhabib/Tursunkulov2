package org.tursunkulov.authorization.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tursunkulov.authorization.entity.User;
import org.tursunkulov.authorization.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  @Cacheable("users")
  public Optional<List<User>> allUsers() {
    return Optional.ofNullable(userRepository.allUsers());
  }

  @Transactional
  @CachePut(value = "users", key = "#userId.toInt()")
  public Optional<User> findUserById(int id) {
    return userRepository.findUserById(id);
  }

  @Transactional
  @CacheEvict(value = "users", key = "#userId.toInt()")
  public void deleteUserById(int id) {
    userRepository.deleteById(id);
  }

  @Transactional
  @CacheEvict(value = "username", key = "#username.toString()")
  public void deleteUserByUsername(String username) {
    userRepository.deleteByUsername(username);
  }

  @Transactional
  @CachePut(value = "users", key = "#userId.toInt()")
  public Optional<User> patchPhoneNumber(int id, String phoneNumber) {
    return userRepository.patchPhoneNumber(id, phoneNumber);
  }

  @Transactional
  @CachePut(value = "users", key = "#userId.toInt()")
  public Optional<User> patchEmail(int id, String email) {
    return userRepository.patchEmail(id, email);
  }

  @Transactional
  @CachePut(value = "user", key = "#userId.toInt()")
  public void updateUserById(int id, User user) {
    userRepository.updateUserById(id, user);
  }

  @Transactional
  @CacheEvict(value = "user", allEntries = true)
  public void updateUserByUsername(String username, User user) {
    userRepository.updateUserByUsername(username, user);
  }
}
