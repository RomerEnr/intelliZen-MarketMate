package me.romeralvarez.intellizenmarketmate.shared.infrastructure.security.config;

import me.romeralvarez.intellizenmarketmate.shared.domain.AttributesConstants;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Value("${market-mate.jwt.converter.principal-attribute}")
  private String principalAttribute;

  @Value("${market-mate.jwt.converter.resource-id}")
  private String resourceId;

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    Stream<GrantedAuthority> defaultStream = Stream.empty();
    Stream<GrantedAuthority> jwtAuthoritiesStream = Optional.of(jwtGrantedAuthoritiesConverter.convert(jwt))
        .map(Collection::stream)
        .orElse(defaultStream);

    Collection<GrantedAuthority> authorities = Stream.concat(jwtAuthoritiesStream, extractResourceRoles(jwt).stream())
        .toList();

    String userId = jwt.getSubject();
    if (userId != null) {
      UserId userIdValueObject = new UserId(UUID.fromString(userId));
      RequestContextHolder.currentRequestAttributes().setAttribute(AttributesConstants.USER_ID, userIdValueObject, RequestAttributes.SCOPE_REQUEST);
    }

    return new JwtAuthenticationToken(jwt, authorities, getPrincipalName(jwt));
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    Map<String, Object> resourceAccess;
    Map<String, Object> resource;
    Collection<String> resourceRoles;

    if (jwt.getClaim("resource_access") == null) {
      return Set.of();
    }
    resourceAccess = jwt.getClaim("resource_access");

    if (resourceAccess.get(resourceId) == null) {
      return Set.of();
    }
    resource = (Map<String, Object>) resourceAccess.get(resourceId);

    if (resource.get("roles") == null) {
      return Set.of();
    }
    resourceRoles = (Collection<String>) resource.get("roles");

    return resourceRoles.stream()
        .map(roleName -> "ROLE_" + roleName)
        .map(roleName -> (GrantedAuthority) () -> roleName)
        .toList();
  }

  private String getPrincipalName(Jwt jwt) {
    String claimName = JwtClaimNames.SUB;

    if (jwt.getClaim(principalAttribute) != null) {
      claimName = principalAttribute;
    }
    return jwt.getClaim(claimName);
  }
}
