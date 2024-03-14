package me.romeralvarez.intellizenmarketmate.shared.infraestructure.event.rabbitmq;

import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.EventBus;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Component;

import java.util.List;

public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String            exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher         = publisher;
        this.exchangeName      = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException error) {
            throw new RuntimeException("Failed to publish event: " + domainEvent.getClass().getSimpleName(), error);

        }
    }
}
