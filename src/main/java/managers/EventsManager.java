package managers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import events.*;

public class EventsManager {
    private LinkedList<Event> events = new LinkedList<>();
    private Map<Class<? extends Event>, LinkedList<Event>> eventsPerType = new HashMap<>();


    public void add(Event event) {
        events.add(event);

        Class<? extends Event> eventType = event.getClass();
        eventsPerType
                .computeIfAbsent(eventType, k -> new LinkedList<Event>())
                .add(event);
    }

    public Event findByTitle(String title) {
        for (Event event : events) {
            if (event.getTitle().toLowerCase() == title.toLowerCase())
                return event;
        }

        return null;
    }

    public void showReport() {
        Class<? extends Event> eventType;
        LinkedList<Event> eventsRegistered;

        for (Map.Entry<Class<? extends Event>, LinkedList<Event>> typeEvents : eventsPerType.entrySet()) {
            eventType = typeEvents.getKey();
            eventsRegistered = typeEvents.getValue();

            System.out.println(eventType.getSimpleName());

            for (Event event : eventsRegistered) {
                System.out.println(event.getTitle() + " " + event.getReadableDate());
            }

            System.out.println();
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}
