package com.mobileservices.warehouse.security;

import com.mobileservices.warehouse.security.jwt.JwtAuthenticationEntryPoint;
import com.mobileservices.warehouse.security.jwt.JwtTokenAuthenticationFilter;
import com.mobileservices.warehouse.security.jwt.JwtUtils;
import com.mobileservices.warehouse.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final JwtUtils jwtUtils;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Configure DB authentication provider for user accounts
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
      .and()
      .addFilterBefore(new JwtTokenAuthenticationFilter(userDetailsService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers("/api/v1/users/register**", "/api/v1/users/register/google**", "/api/v1/users/login**").permitAll()
      .antMatchers("/oauth2/**").permitAll()
      .antMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAuthority(Role.WAREHOUSE_MANAGER.getName())
      .anyRequest().authenticated();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().mvcMatchers("/api/v1/users/register**", "/oauth2/**", "/api/v1/users/register/google**",
      "/api/v1/users/login**");
  }
}
