package com.mobileservices.warehouse.user.service;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.security.jwt.CustomJwtToken;
import com.mobileservices.warehouse.security.jwt.JwtUtils;
import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserDetailsService userDetailsService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public void register(UserApi userApi) {

    checkIfUserWithUsernameExists(userApi.getUsername());

    User user = userMapper.transformToDbModel(userApi);
    userRepository.save(user);
  }

  public CustomJwtToken login(String username, String password) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, password));

    String accessToken = jwtUtils.generateAccessToken(authentication);
    String refreshToken = jwtUtils.generateRefreshToken(authentication);

    return CustomJwtToken.builder()
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .build();
  }

  public CustomJwtToken getAccessTokenFromGoogleToken(String googleToken) {
    try {
      IdToken token = IdToken.parse(JacksonFactory.getDefaultInstance(), googleToken);
      String googleEmail = (String) token.getPayload().get("email");

      UserDetails userDetails = registerOrFetch(googleEmail);

      String accessToken = jwtUtils.generateAccessToken(userDetails);
      String refreshToken = jwtUtils.generateRefreshToken(userDetails);

      return CustomJwtToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();

    } catch (IOException e) {
      throw new BadRequestException("Token could not be parsed");
    }
  }

  private void checkIfUserWithUsernameExists(String username) {

    Optional<User> existingUser = userRepository.findByUsername(username);

    if (existingUser.isPresent()) {
      throw new BadRequestException("User already exists");
    }
  }

  private UserDetails registerOrFetch(String email) {
    Optional<User> existingUser = userRepository.findByUsername(email);

    if (!existingUser.isPresent()) {
      User newUser = userMapper.transformGoogleUser(email);
      userRepository.save(newUser);
    }
    return userDetailsService.loadUserByUsername(email);
  }
}
