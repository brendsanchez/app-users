package com.bci.users.controller;

import com.bci.users.dto.UserDto;
import com.bci.users.dto.response.ErrorResponse;
import com.bci.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "User", description = "Operations related to user management such as creating, getting .. etc.")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Find user by token.",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> authenticatedUser() {
        return ResponseEntity.ok(this.userService.authenticatedUser());
    }

    @Operation(summary = "Find all users.",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> allUsers() {
        return ResponseEntity.ok(this.userService.allUsers());
    }
}