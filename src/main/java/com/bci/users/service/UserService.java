package com.bci.users.service;

import com.bci.users.dto.UserDto;
import com.bci.users.exception.UserException;
import com.bci.users.model.User;
import com.bci.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto authenticatedUser() {
        log.info(":::::: finding authenticated user: :::::");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .createdDate(user.getCreatedDate())
                    .updatedDate(user.getUpdatedDate())
                    .lastLogin(user.getLastLogin())
                    .inactive(user.getInactive())
                    .build();
        }

        throw new UserException(HttpStatus.UNAUTHORIZED, "se requiere un token valido");
    }

    public List<UserDto> allUsers() {
        log.info(":::::: finding all users: :::::");

        var users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserException(HttpStatus.NOT_FOUND, "No se encontraron usuarios");
        }

        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .createdDate(user.getCreatedDate())
                        .updatedDate(user.getUpdatedDate())
                        .lastLogin(user.getLastLogin())
                        .inactive(user.getInactive())
                        .build())
                .toList();
    }
}