package me.romeralvarez.intellizenmarketmate.invoice.application;

import me.romeralvarez.intellizenmarketmate.invoice.domain.events.InvoiceFileUploaded;
import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({InvoiceFileUploaded.class})
public class FileCreatedEventHandler {

  @EventListener
  public void on(InvoiceFileUploaded event) {
    System.out.println("Invoice file uploaded event received:");
    System.out.println("User ID: " + event.getUserId().value().toString());
    System.out.println("File URL: " + event.getFileUrl().value());
    System.out.println("Created at: " + event.getCreatedAt().toString());
    System.out.println("Updated at: " + event.getUpdatedAt().toString());
  }
}
