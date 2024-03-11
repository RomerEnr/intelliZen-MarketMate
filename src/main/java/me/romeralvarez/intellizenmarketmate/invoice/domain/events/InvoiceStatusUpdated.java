package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceStatus;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class InvoiceStatusUpdated extends DomainEvent {
  private final InvoiceStatus invoiceStatus;

  public InvoiceStatusUpdated(String aggregateId, InvoiceStatus invoiceStatus) {
    super(aggregateId);
    this.invoiceStatus = invoiceStatus;
  }

  public InvoiceStatusUpdated(String aggregateId, String eventId, String occurredOn, InvoiceStatus invoiceStatus) {
    super(aggregateId, eventId, occurredOn);
    this.invoiceStatus = invoiceStatus;
  }

  @Override
  public String eventName() {
    return "invoice.status_updated";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("invoiceStatus", invoiceStatus.toString());
    }};
  }

  @Override
  public InvoiceStatusUpdated fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    return new InvoiceStatusUpdated(
        aggregateId,
        eventId,
        occurredOn,
        InvoiceStatus.valueOf((String) body.get("invoiceStatus"))
    );
  }
}
