package com.bci.users.dto.request;

import com.bci.users.dto.PhoneDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "param 'name' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Juan Rodriguez", description = "user's name")
    private String name;

    @NotNull(message = "param 'email' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "juan@rodriguez.org", description = "user's email")
    private String email;

    @NotNull(message = "param 'password' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "hunter2", description = "user's password")
    private String password;

    @Valid
    @NotNull(message = "param 'phones' is required")
    @Size(min = 1, message = "param 'phones' should have at least one phone")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "user's phones")
    List<PhoneDto> phones;
}