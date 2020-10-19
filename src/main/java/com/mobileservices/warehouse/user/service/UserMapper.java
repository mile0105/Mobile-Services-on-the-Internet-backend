package com.mobileservices.warehouse.user.service;

import com.mobileservices.warehouse.user.model.Role;
import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.model.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

}
