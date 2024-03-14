package me.romeralvarez.intellizenmarketmate.shared.infraestructure.security.filter;

import me.romeralvarez.intellizenmarketmate.shared.domain.HeaderConstants;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtAuthenticationWebFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> securityContext.getAuthentication())
        .filter(authentication -> authentication.getPrincipal() instanceof Jwt)
        .doOnNext(authentication -> {
          Jwt jwt = (Jwt) authentication.getPrincipal();

          // Extract the necessary information
          String userId = jwt.getClaimAsString("sub");
          String email = jwt.getClaimAsString("email");
          List<String> roles = jwt.getClaimAsStringList("roles"); // Assuming this method exists and returns a List<String>

          // Instead of mutating the request, set attributes on the exchange
          exchange.getAttributes().put(HeaderConstants.USER_ID_HEADER, userId);
          exchange.getAttributes().put(HeaderConstants.EMAIL_HEADER, email);
          exchange.getAttributes().put(HeaderConstants.ROLE_HEADER, String.join(",", roles));
        })
        .then(chain.filter(exchange));
  }
}