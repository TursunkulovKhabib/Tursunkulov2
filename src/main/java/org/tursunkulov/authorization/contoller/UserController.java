package org.tursunkulov.authorization.contoller;

import org.springframework.stereotype.Controller;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.UserRepository;

@Controller
public class UserController {

    public static boolean findUser(String username, String password) {
        for(User user: UserRepository.getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
