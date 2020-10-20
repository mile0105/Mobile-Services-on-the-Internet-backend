package com.mobileservices.warehouse.security;

import com.mobileservices.warehouse.user.model.User;
import com.mobileservices.warehouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
      Collections.singletonList(authority));

  }
}
