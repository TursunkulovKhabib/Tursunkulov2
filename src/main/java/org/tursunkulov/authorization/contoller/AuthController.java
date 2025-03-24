package org.tursunkulov.authorization.contoller;

import org.springframework.web.bind.annotation.*;
import org.tursunkulov.authorization.model.User;
import org.tursunkulov.authorization.repository.UserRepository;
import org.tursunkulov.authorization.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthController {

    @PostMapping("/registartion")
    public static String registration(@RequestParam int id, @RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email,
                                      @RequestParam String phoneNumber) {
        User user = new User(id, username, password, email, phoneNumber);
        return UserRepository.saveUser(user);
    }

    @PostMapping("/authorization")
    public String authorization(@RequestParam String username,
                                @RequestParam String password) {
        return UserRepository.checkUser(username, password);
    }

    @GetMapping("/info")
    public List<User> info() {
        return UserService.allUsers();
    }
}
