package org.tursunkulov.authorization.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.tursunkulov.authorization.contoller.UserController;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class UserRepository {

    @Getter
    private static List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "Пётр", "1234", "1212@g.com", "88005555555"));
        users.add(new User(2, "Andrew", "1234", "1sds12@g.com", "88885555555"));
    }

    public static String saveUser(User user) {
        users.add(user);
        return UserService.newUser();
    }

    public static String checkUser(String username, String password) {
        if (users.isEmpty()) {
            UserService.registration();
        } else {
            if (UserController.findUser(username, password)) {
                 return UserService.authorisation();
            } else {
                 return UserService.incorrectData();
            }
        }
        return "Error";
    }

}
