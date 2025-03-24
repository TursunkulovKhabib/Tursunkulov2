package org.tursunkulov.authorization.repository;

import lombok.Getter;
import org.tursunkulov.authorization.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class AuthRepository {

  @Getter
  private static List<User> users = new ArrayList<>();

  public AuthRepository() {
    users.add(new User(1, "Пётр", "1234", "1212@g.com", "88005555555"));
    users.add(new User(2, "Andrew", "1234", "1sds12@g.com", "88885555555"));
  }

  public static String saveUser(User user) {
    users.add(user);
    return "Success";
  }

  public static String checkUser(String username, String password) {
    if (users.isEmpty()) {
      return "Зарегистрируйте пользователя";
    } else if (UserRepository.findUser(username, password)) {
      return "Добро пожаловать!";
    }
    return "Проверьте корректность данных";
  }

}
