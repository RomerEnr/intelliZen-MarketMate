package me.romeralvarez.intellizenmarketmate.shared.infraestructure;

import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public final class DomainEventsInformation {
    HashMap<String, Class<? extends DomainEvent>> indexedDomainEvents;

    public DomainEventsInformation() {
        Reflections reflections = new Reflections("me.romeralvarez.intellizenmarketmate.shared.domain.bus.event");
        Set<Class<? extends DomainEvent>> classes     = reflections.getSubTypesOf(DomainEvent.class);
        System.out.println("Found domain events: " + classes);

        try {
            indexedDomainEvents = formatEvents(classes);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Class<? extends DomainEvent> forName(String name) {
        return indexedDomainEvents.get(name);
    }

    public String forClass(Class<? extends DomainEvent> domainEventClass) {
        return indexedDomainEvents.entrySet()
                                  .stream()
                                  .filter(entry -> Objects.equals(entry.getValue(), domainEventClass))
                                  .map(Map.Entry::getKey)
                                  .findFirst().orElse("");
    }

    private HashMap<String, Class<? extends DomainEvent>> formatEvents(
        Set<Class<? extends DomainEvent>> domainEvents
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        HashMap<String, Class<? extends DomainEvent>> events = new HashMap<>();

        for (Class<? extends DomainEvent> domainEvent : domainEvents) {
            DomainEvent nullInstance = domainEvent.getConstructor().newInstance();

            events.put((String) domainEvent.getMethod("eventName").invoke(nullInstance), domainEvent);
        }

        return events;
    }
}