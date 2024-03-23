package me.romeralvarez.intellizenmarketmate.shared.domain.vo;

import java.util.UUID;

public record UserId(UUID value) {

  public static UserId fromString(String value) {
    return new UserId(UUID.fromString(value));
  }

  @Override
  public UUID value() {
    return value;
  }
}
