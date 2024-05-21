package com.bci.users.controller;

import com.bci.users.dto.request.LoginUserRequest;
import com.bci.users.dto.request.UserRequest;
import com.bci.users.dto.response.ErrorResponse;
import com.bci.users.dto.response.LoginResponse;
import com.bci.users.dto.response.UserResponse;
import com.bci.users.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operations authentication.")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Create a new user.",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserRequest registerUserDto) {
        var userResponse = this.authenticationService.signup(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Operation(summary = "Gets an user's authentication token.",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        return ResponseEntity.ok(this.authenticationService.authenticate(loginUserRequest));
    }
}