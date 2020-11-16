package com.mobileservices.warehouse.user.service;

import com.mobileservices.warehouse.user.model.Role;
import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.model.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public User transformToDbModel(UserApi userApi) {
    return User.builder()
      .username(userApi.getUsername())
      .password(passwordEncoder.encode(userApi.getPassword()))
      .role(Role.WAREHOUSE_EMPLOYEE.getName())
      .build();
  }

  public User transformGoogleUser(String email) {

    String randomPassword = generateRandomString();

    return User.builder()
      .username(email)
      .password(passwordEncoder.encode(randomPassword))
      .role(Role.WAREHOUSE_EMPLOYEE.getName())
      .build();
  }

  private String generateRandomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
  }

}
