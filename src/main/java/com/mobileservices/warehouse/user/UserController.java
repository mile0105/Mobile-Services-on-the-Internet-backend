package com.mobileservices.warehouse.user;

import com.mobileservices.warehouse.security.jwt.CustomJwtToken;
import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.service.UserService;
import com.mobileservices.warehouse.util.models.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<EmptyResponse> register(@RequestBody UserApi userApi) {

    userService.register(userApi);
    return ResponseEntity.ok(new EmptyResponse());
  }

  @PostMapping("/register/google")
  public ResponseEntity<CustomJwtToken> parseToken(@RequestBody String googleToken) {
    CustomJwtToken jwtToken = userService.getAccessTokenFromGoogleToken(googleToken);
    return ResponseEntity.ok(jwtToken);
  }

  @PostMapping("/login")
  public ResponseEntity<CustomJwtToken> login(@RequestBody UserApi userApi) {
    CustomJwtToken jwtToken = userService.login(userApi.getUsername(), userApi.getPassword());
    return ResponseEntity.ok(jwtToken);
  }
}
