package org.tursunkulov.authorization.service;

import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tursunkulov.authorization.model.AuditEventDto;
import org.tursunkulov.authorization.kafka.AuditProducer;
import org.tursunkulov.authorization.entity.User;
import org.tursunkulov.authorization.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final AuditProducer auditProducer;

  @Transactional
  @Cacheable("users")
  public Optional<List<User>> allUsers() {
    return Optional.ofNullable(userRepository.allUsers());
  }

  @Transactional
  @CachePut(value = "users", key = "#id")
  public Optional<User> findUserById(int id) {
    return userRepository.findUserById(id);
  }

  @Transactional
  @CacheEvict(value = "users", key = "#id")
  public void deleteUserById(int id) {
    userRepository.deleteById(id);
    sendAudit("DELETE_BY_ID:" + id, null);
  }

  @Transactional
  @CacheEvict(value = "username", key = "#username")
  public void deleteUserByUsername(String username) {
    userRepository.deleteByUsername(username);
    sendAudit("DELETE_BY_USERNAME:" + username, null);
  }

  @Transactional
  @CachePut(value = "users", key = "#id")
  public Optional<User> patchPhoneNumber(int id, String phoneNumber) {
    var result = userRepository.patchPhoneNumber(id, phoneNumber);
    result.ifPresent(user -> sendAudit("PATCH_PHONE:" + id, user.getId()));
    return result;
  }

  @Transactional
  @CachePut(value = "users", key = "#id")
  public Optional<User> patchEmail(int id, String email) {
    var result = userRepository.patchEmail(id, email);
    result.ifPresent(user -> sendAudit("PATCH_EMAIL:" + id, user.getId()));
    return result;
  }

  @Transactional
  @CachePut(value = "user", key = "#id")
  public void updateUserById(int id, User user) {
    userRepository.updateUserById(id, user);
    sendAudit("UPDATE_BY_ID:" + id, user.getId());
  }

  @Transactional
  @CacheEvict(value = "user", allEntries = true)
  public void updateUserByUsername(String username, User user) {
    userRepository.updateUserByUsername(username, user);
    sendAudit("UPDATE_BY_USERNAME:" + username, user.getId());
  }

  private void sendAudit(String action, Integer userUuid) {
    UUID id = userUuid != null
            ? UUID.nameUUIDFromBytes(userUuid.toString().getBytes())
            : UUID.randomUUID();
    AuditEventDto event = AuditEventDto.builder()
            .eventId(UUID.randomUUID())
            .userId(id)
            .action(action)
            .timestamp(Instant.now())
            .build();
    auditProducer.send(event);
  }
}
