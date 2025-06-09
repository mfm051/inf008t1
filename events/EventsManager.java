package events;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import attendees.*;

public class EventsManager {
    private LinkedList<Event> events = new LinkedList<>();
    private Map<Class<? extends Event>, LinkedList<Event>> eventsPerType = new HashMap<>();
    private LinkedList<Attendee> attendees = new LinkedList<>();

    public LinkedList<Event> getEvents() {
        return events;
    };

    public void addEvent(Event event) {
        events.add(event);

        Class<? extends Event> eventType = event.getClass();
        eventsPerType
                .computeIfAbsent(eventType, k -> new LinkedList<Event>())
                .add(event);
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public String getEventsReport() {
        Class<? extends Event> eventType;
        LinkedList<Event> eventsRegistered;

        String report = "";

        for (Map.Entry<Class<? extends Event>, LinkedList<Event>> typeEvents : eventsPerType.entrySet()) {
            eventType = typeEvents.getKey();
            eventsRegistered = typeEvents.getValue();

            report += eventType.getSimpleName();
            report += "\n";

            for (Event event : eventsRegistered) {
                report += event.getTitle() + " " + event.getReadableDate();
                report += "\n";
            }

            report += "\n\n";
        }

        return report;
    }
}
