package org.tursunkulov.authorization.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.tursunkulov.authorization.contoller.AuthController;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication Api", description = "Операции для аутентификации")
public interface AuthControllerApi {

}
