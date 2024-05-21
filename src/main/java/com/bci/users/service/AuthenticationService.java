package com.bci.users.service;

import com.bci.users.dto.request.LoginUserRequest;
import com.bci.users.dto.request.UserRequest;
import com.bci.users.dto.response.LoginResponse;
import com.bci.users.dto.response.UserResponse;
import com.bci.users.exception.UserException;
import com.bci.users.model.Phone;
import com.bci.users.model.User;
import com.bci.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${regexp.email}")
    private String regexpEmail;

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserResponse signup(UserRequest request) {
        try {
            log.info(":::::: starting create user: {} :::::", request);

            if (!this.emailPattern().matcher(request.getEmail()).matches()) {
                throw new UserException(HttpStatus.BAD_REQUEST,
                        "Correo formato incorrecto: ".concat(request.getEmail()));
            }

            Optional<User> userOptional = this.userRepository.findByEmail(request.getEmail());
            if (userOptional.isPresent()) {
                throw new UserException(HttpStatus.CONFLICT, "Correo ya registrado");
            }

            final var uuid = UUID.randomUUID();
            final var password = this.passwordEncoder.encode(request.getPassword());
            final var user = User.builder()
                    .id(uuid)
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(password)
                    .inactive(false)
                    .build();

            var phones = request.getPhones()
                    .stream()
                    .map(phone -> Phone.builder()
                            .user(user)
                            .number(phone.getNumber())
                            .cityCode(phone.getCityCode())
                            .countryCode(phone.getCountryCode())
                            .build())
                    .toList();

            user.setPhoneList(phones);

            final var token = this.tokenService.generateToken(user);
            user.setToken(token);

            log.debug("saving user by id: {}", uuid);
            var userSaved = this.userRepository.save(user);
            return UserResponse.builder()
                    .userId(userSaved.getId())
                    .created(userSaved.getCreatedDate())
                    .modified(userSaved.getUpdatedDate())
                    .lastLogin(userSaved.getLastLogin())
                    .token(userSaved.getToken())
                    .inactive(userSaved.getInactive())
                    .build();
        } catch (DataAccessException ex) {
            throw new UserException(ex);
        }
    }

    public LoginResponse authenticate(LoginUserRequest loginUserRequest) {
        log.info(":::::: starting authenticate with loginUserRequest: {} :::::", loginUserRequest);

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginUserRequest.getEmail(),
                loginUserRequest.getPassword());

        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = this.userRepository.findByEmail(loginUserRequest.getEmail())
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, "No se encontro usuario"));

        var jwtToken = this.tokenService.generateToken(user);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(tokenService.getExpirationTime())
                .build();
    }

    private Pattern emailPattern() {
        return Pattern.compile(this.regexpEmail, Pattern.CASE_INSENSITIVE);
    }
}
