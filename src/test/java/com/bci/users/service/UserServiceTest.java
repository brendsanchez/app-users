package com.bci.users.service;

import com.bci.users.exception.UserException;
import com.bci.users.repository.UserRepository;
import com.bci.users.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void whenAuthenticatedUserOk() {
        UserDetails userDetails = TestUtils.userFound();
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var response = this.userService.authenticatedUser();
        assertEquals(TestUtils.UUID_TEST, response.getId());
        assertEquals(TestUtils.EMAIL, response.getEmail());
    }

    @Test
    void whenAuthenticatedUserFail() {
        var authentication = new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var exception = assertThrows(UserException.class, () -> {
            this.userService.authenticatedUser();
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getErrorResponse().getStatus());
        assertEquals("se requiere un token valido", exception.getErrorResponse().getMessage());
    }

    @Test
    void whenFindAllUsersOk() {
        when(this.userRepository.findAll()).thenReturn(Collections.singletonList(TestUtils.userFound()));

        var response = this.userService.allUsers();

        assertFalse(response.isEmpty());

        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void whenFindAllUsersNotFound() {
        when(this.userRepository.findAll()).thenReturn(Collections.emptyList());

        var exception = assertThrows(UserException.class, () -> {
            this.userService.allUsers();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorResponse().getStatus());
        assertEquals("No se encontraron usuarios", exception.getErrorResponse().getMessage());

        verify(this.userRepository, times(1)).findAll();
    }

}