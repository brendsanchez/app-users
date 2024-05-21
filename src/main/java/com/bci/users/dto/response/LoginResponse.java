package com.bci.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Token")
    private String token;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Tiempo de expiracion")
    private long expiresIn;
}