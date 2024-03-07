package me.romeralvarez.intellizenmarketmate.invoice.domain.service;

import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceStatus;

public class InvoiceStatusTransition {
  public boolean canTransitionTo(InvoiceStatus currentStatus, InvoiceStatus newStatus) {
    if (currentStatus == InvoiceStatus.PENDING) {
      return newStatus == InvoiceStatus.PROCESSED;
    } else if (currentStatus == InvoiceStatus.PROCESSED) {
      return newStatus == InvoiceStatus.REVIEWED;
    } else if (currentStatus == InvoiceStatus.REVIEWED) {
      return false;
    }
    return false;
  }
}
