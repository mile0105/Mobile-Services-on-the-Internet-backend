package com.mobileservices.warehouse.user;

import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> register(@RequestBody UserApi userApi) {

    userService.register(userApi);
    return ResponseEntity.ok().build();
  }
}
