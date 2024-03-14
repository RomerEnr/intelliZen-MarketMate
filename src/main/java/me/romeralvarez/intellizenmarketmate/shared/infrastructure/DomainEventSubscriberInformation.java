package me.romeralvarez.intellizenmarketmate.shared.infrastructure;


import me.romeralvarez.intellizenmarketmate.shared.domain.Utils;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.util.List;


public record DomainEventSubscriberInformation(Class<?> subscriberClass,
                                               List<Class<? extends DomainEvent>> subscribedEvents) {

    public String contextName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public String formatRabbitMqQueueName() {
        return String.format("romeralvarez.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }
}
