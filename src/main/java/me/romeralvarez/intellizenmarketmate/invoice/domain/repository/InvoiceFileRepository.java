package me.romeralvarez.intellizenmarketmate.invoice.domain.repository;

import me.romeralvarez.intellizenmarketmate.invoice.domain.InvoiceFile;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceFileId;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;

import java.util.List;
import java.util.Optional;

public interface InvoiceFileRepository {
  void save(InvoiceFile invoiceFile);
  Optional<InvoiceFile> findById(InvoiceFileId id);
  List<InvoiceFile> findByUserId(UserId userId);
}
