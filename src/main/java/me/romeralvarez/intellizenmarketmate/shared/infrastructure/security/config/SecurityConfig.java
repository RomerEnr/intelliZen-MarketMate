package me.romeralvarez.intellizenmarketmate.shared.infrastructure.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

  public static final String LOGIN_ENDPOINT = "/api/auth/login";
  private final JwtAuthenticationConverter jwtAuthenticationConverter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers(LOGIN_ENDPOINT).permitAll()
                .anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 ->
            oauth2.jwt(jwtConfigurer ->
                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterAfter(new JwtHeadersFilter(), BearerTokenAuthenticationFilter.class)
        .build();
  }

  private static class JwtHeadersFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getSubject();
        String email = jwt.getClaim("email");
        List<String> roles = jwt.getClaimAsStringList("roles");

        if (userId != null) {
          request.setAttribute("X-User-Id", userId);
        }
        if (email != null) {
          request.setAttribute("X-User-Email", email);
        }
        if (roles != null && !roles.isEmpty()) {
          request.setAttribute("Roles", String.join(",", roles));
        }
      }
      filterChain.doFilter(request, response);
    }
  }
}