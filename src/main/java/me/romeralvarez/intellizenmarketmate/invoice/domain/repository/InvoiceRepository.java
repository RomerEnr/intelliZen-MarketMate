package me.romeralvarez.intellizenmarketmate.invoice.domain.repository;

import me.romeralvarez.intellizenmarketmate.invoice.domain.Invoice;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceId;

import java.util.Optional;

public interface InvoiceRepository {
  void save(Invoice invoice);
  Optional<Invoice> findById(InvoiceId id);
}
