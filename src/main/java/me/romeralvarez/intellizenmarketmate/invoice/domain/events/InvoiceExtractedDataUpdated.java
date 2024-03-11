package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.*;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.SupermarketId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InvoiceExtractedDataUpdated extends DomainEvent {
  private final InvoiceData extractedData;

  public InvoiceExtractedDataUpdated(String aggregateId, InvoiceData extractedData) {
    super(aggregateId);
    this.extractedData = extractedData;
  }

  public InvoiceExtractedDataUpdated(String aggregateId, String eventId, String occurredOn, InvoiceData extractedData) {
    super(aggregateId, eventId, occurredOn);
    this.extractedData = extractedData;
  }

  @Override
  public String eventName() {
    return "invoice.extracted_data_updated";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("supermarketId", extractedData.supermarketId().value().toString());
      put("totalAmount", extractedData.totalAmount().value());
      put("lineItems", new ArrayList<>(extractedData.lineItems().stream()
          .map(lineItem -> new HashMap<String, Serializable>() {{
            put("name", lineItem.name().value());
            put("quantity", lineItem.quantity().value());
            put("unitPrice", lineItem.unitPrice().value());
            put("totalPrice", lineItem.totalPrice().value());
          }})
          .collect(Collectors.toList())));
    }};
  }

  @Override
  public InvoiceExtractedDataUpdated fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    SupermarketId supermarketId = new SupermarketId(UUID.fromString((String) body.get("supermarketId")));
    TotalAmount totalAmount = new TotalAmount((BigDecimal) body.get("totalAmount"));

    List<LineItem> lineItems = ((List<HashMap<String, Serializable>>) body.get("lineItems"))
        .stream()
        .map(lineItemMap -> new LineItem(
            new ItemName((String) lineItemMap.get("name")),
            new ItemQuantity((BigDecimal) lineItemMap.get("quantity")),
            new ItemUnitPrice((BigDecimal) lineItemMap.get("unitPrice")),
            new ItemTotalPrice((BigDecimal) lineItemMap.get("totalPrice"))
        ))
        .collect(Collectors.toList());

    InvoiceData extractedData = new InvoiceData(supermarketId, totalAmount, lineItems);

    return new InvoiceExtractedDataUpdated(
        aggregateId,
        eventId,
        occurredOn,
        extractedData
    );
  }
}
