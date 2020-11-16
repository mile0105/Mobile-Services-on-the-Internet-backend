package com.mobileservices.warehouse.security.jwt;

import com.mobileservices.warehouse.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;
  private final JwtUtils jwtUtils;

  static final String ACCESS_TOKEN_PREFIX = "Bearer ";
  static final String REFRESH_TOKEN_PREFIX = "Refresh ";
  private static final String AUTHORIZATION_HEADER = "Authorization";


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {

    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
    String username = null;

    if (authorizationHeader == null || !authorizationHeader.startsWith(ACCESS_TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    String token = authorizationHeader.replace(ACCESS_TOKEN_PREFIX, "");

    try {

      String tokenType = jwtUtils.getTokenType(token);
      if (ACCESS_TOKEN_PREFIX.equals(tokenType)) {
        username = jwtUtils.getUsernameFromToken(token);
      }
    } catch (IllegalArgumentException e) {
      logger.warn("Unable to get JWT");
    } catch (ExpiredJwtException e) {
      logger.warn("JWT is expired");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (jwtUtils.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }
}
