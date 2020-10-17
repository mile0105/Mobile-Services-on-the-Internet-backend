package com.mobileservices.warehouse.user.service;

import com.mobileservices.warehouse.user.model.UserApi;
import com.mobileservices.warehouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void register(UserApi userApi) {


  }


}
