package org.tursunkulov.authorization.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    public static void registration() {
        User newUser = new User(0, "Mitya", "121213123", "ffff@g.com", "88003432221");
        UserRepository.saveUser(newUser);
    }

    public static List<User> allUsers() {
        log.debug("Получение списка всех пользователей");
        return UserRepository.getUsers();
    }

    public static String newUser() {
        return "Новый пользователь добавлен";
    }

    public static String authorisation() {
        return "Добро пожаловать!";
    }

    public static String incorrectData() {
        return "Проверьте корректность данных";
    }

}