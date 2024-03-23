package me.romeralvarez.intellizenmarketmate.invoice.infrastructure.repository;

import me.romeralvarez.intellizenmarketmate.invoice.domain.InvoiceFile;
import me.romeralvarez.intellizenmarketmate.invoice.domain.repository.InvoiceFileRepository;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceFileId;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceFileRepositoryMongoAdapter implements InvoiceFileRepository {
  private final MongoTemplate mongoTemplate;

  public InvoiceFileRepositoryMongoAdapter(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void save(InvoiceFile invoiceFile) {
    mongoTemplate.save(invoiceFile);
  }

  @Override
  public Optional<InvoiceFile> findById(InvoiceFileId id) {
    return Optional.ofNullable(mongoTemplate.findById(id.value(), InvoiceFile.class));
  }

  @Override
  public List<InvoiceFile> findByUserId(UserId userId) {
    Query query = new Query(Criteria.where("userId").is(userId));
    return mongoTemplate.find(query, InvoiceFile.class);
  }
}
