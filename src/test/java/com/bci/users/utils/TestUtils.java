package com.bci.users.utils;

import com.bci.users.dto.PhoneDto;
import com.bci.users.dto.request.UserRequest;
import com.bci.users.model.User;

import java.util.Collections;
import java.util.UUID;

public class TestUtils {

    public static final String EMAIL_PATTEN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String EMAIL = "test@gmail.com";
    public static final String TOKEN = "IFWUHEUFIWHE912";
    public static final String PASSWORD = "******";
    public static final Integer NUMBER = 123456789;
    public static final Integer CITY_CODE = 11;
    public static final Integer COUNTRY_CODE = 54;
    public static final UUID UUID_TEST = UUID.randomUUID();

    public static UserRequest userRequest() {
        return UserRequest.builder()
                .email(EMAIL)
                .phones(Collections.singletonList(PhoneDto.builder()
                        .number(NUMBER)
                        .cityCode(CITY_CODE)
                        .countryCode(COUNTRY_CODE)
                        .build()))
                .build();
    }

    public static User userFound() {
        return User.builder()
                .id(UUID_TEST)
                .name("test")
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }
}
