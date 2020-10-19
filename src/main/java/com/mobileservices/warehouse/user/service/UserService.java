package com.mobileservices.warehouse.user.service;

import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public void register(UserApi userApi) {

    User user = userMapper.transformToDbModel(userApi);
    userRepository.save(user);
  }
}
