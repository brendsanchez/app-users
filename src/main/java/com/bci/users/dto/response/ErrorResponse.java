package com.bci.users.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Descripción de error del servicio")
    private String message;

    @JsonIgnore
    private HttpStatus status;
}
