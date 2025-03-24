package org.tursunkulov.authorization.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Tag(name = "Авторизация & Registration Api", description = "Операции для авторизации и регистрации")
public interface AuthControllerApi {

  @Operation(summary = "Авторизация", description = "Вход в аккаунт")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Успешный вход"),
      @ApiResponse(responseCode = "401", description = "NOT_FOUND | Неверно введены данные")})
  ResponseEntity<String> authentication(@RequestParam String username, @RequestParam String password);

  @Operation(summary = "Регистрация", description = "Регистрация данных")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Данные зарегистрированы")
  })
  ResponseEntity<String> registration(@RequestParam int id, @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String phoneNumber);
}
