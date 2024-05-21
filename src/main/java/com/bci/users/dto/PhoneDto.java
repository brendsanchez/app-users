package com.bci.users.dto;

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
public class PhoneDto {

    @NotNull(message = "param 'number' is required")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "123456789", description = "phone's number")
    private Integer number;

    @NotNull(message = "campo 'cityCode' es requerido")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1", description = "phone's city code")
    private Integer cityCode;

    @NotNull(message = "campo 'countryCode' es requerido")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "57", description = "phone's country code")
    private Integer countryCode;
}