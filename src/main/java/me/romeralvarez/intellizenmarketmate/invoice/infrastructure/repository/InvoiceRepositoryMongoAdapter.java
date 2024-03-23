package me.romeralvarez.intellizenmarketmate.invoice.infrastructure.repository;

import me.romeralvarez.intellizenmarketmate.invoice.domain.Invoice;
import me.romeralvarez.intellizenmarketmate.invoice.domain.repository.InvoiceRepository;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InvoiceRepositoryMongoAdapter implements InvoiceRepository {
  private final MongoTemplate mongoTemplate;

  public InvoiceRepositoryMongoAdapter(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void save(Invoice invoice) {
    mongoTemplate.save(invoice);
  }

  @Override
  public Optional<Invoice> findById(InvoiceId id) {
    return Optional.ofNullable(mongoTemplate.findById(id.value(), Invoice.class));
  }
}