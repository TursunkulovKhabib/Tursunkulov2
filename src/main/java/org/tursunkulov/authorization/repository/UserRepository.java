package org.tursunkulov.authorization.repository;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.tursunkulov.authorization.model.User;

@Getter
@Repository
public class UserRepository {

    @Getter
    private static List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "Пётр", "1234", "1212@g.com", "88005555555"));
        users.add(new User(2, "Andrew", "1234", "1sds12@g.com", "88885555555"));
    }

    public static boolean findUser(String username, String password) {
        for(User user: UserRepository.getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static String findUserById(int id) {
        for(User user: UserRepository.getUsers()) {
            if(user.getId() == id) {
                return user.getUsername();
            }
        }
        return "Неверные данные";
    }

    public static void deleteById(int id) {
        for(User user: UserRepository.getUsers()) {
            if(user.getId() == id) {
                users.remove(user);
            }
        }
    }

    public static void deleteByUsername(String username) {
        for(User user: UserRepository.getUsers()) {
            if(user.getUsername().equals(username)) {
                users.remove(user);
            }
        }
    }

    public static User patchPhoneNumber(int id, String phoneNumber) {
        for(User user: UserRepository.getUsers()) {
            if(user.getId() == id) {
                user.setPhoneNumber(phoneNumber);
            }
        }
        return users.get(id);
    }

    public static User patchEmail(int id, String email) {
        for(User user: UserRepository.getUsers()) {
            if(user.getId() == id) {
                user.setEmail(email);
            }
        }
        return users.get(id);
    }

    public static void updateUserById(int id, User user) {
        for(User users: UserRepository.getUsers()) {
            if(users.getId() == id) {
                users.setUsername(user.getUsername());
                users.setPassword(user.getPassword());
                users.setEmail(user.getEmail());
                users.setPhoneNumber(user.getPhoneNumber());
            }
        }
    }

    public static void updateUserByUsername(String username, User user) {
        for(User users: UserRepository.getUsers()) {
            if(users.getUsername().equals(username)) {
                users.setUsername(user.getUsername());
                users.setPassword(user.getPassword());
                users.setEmail(user.getEmail());
                users.setPhoneNumber(user.getPhoneNumber());
            }
        }
    }
}
