package me.romeralvarez.intellizenmarketmate.shared.domain;

public class DomainError {
  private final String message;

   protected DomainError(String message) {
     this.message = message;
   }
   public String getMessage() {
     return message;
   }
}
