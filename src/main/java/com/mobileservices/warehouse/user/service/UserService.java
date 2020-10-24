package com.mobileservices.warehouse.user.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public void register(UserApi userApi) {

    checkIfUserWithUsernameExists(userApi.getUsername());

    User user = userMapper.transformToDbModel(userApi);
    userRepository.save(user);
  }

  private void checkIfUserWithUsernameExists(String username) {

    Optional<User> existingUser = userRepository.findByUsername(username);

    if(existingUser.isPresent()) {
      throw new BadRequestException("User already exists");
    }
  }
}
