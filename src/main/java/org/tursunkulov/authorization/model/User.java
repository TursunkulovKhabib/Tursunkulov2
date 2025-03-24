package org.tursunkulov.authorization.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Schema(description = "Уникальный идентификатор пользователя")
  private int id;

  @NotNull
  @Schema(description = "Уникальное пользовательское имя")
  @Size(min = 1, max = 20)
  private String username;

  @NotNull
  @Schema(description = "Пароль")
  @Size(min = 1)
  private String password;

  @NotBlank
  @Schema(description = "Электронная почта")
  private String email;

  @NotBlank
  @Schema(description = "Номер телефона")
  private String phoneNumber;
}
