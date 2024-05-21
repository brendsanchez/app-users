package com.bci.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {

    @NotNull(message = "param 'email' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "juan@rodriguez.org", description = "user's email")
    private String email;

    @NotNull(message = "param 'password' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "hunter2", description = "user's password")
    private String password;
}
