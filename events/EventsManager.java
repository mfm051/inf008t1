package events;

import java.util.LinkedList;
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;

import attendees.*;

public class EventsManager {
    private LinkedList<Event> events = new LinkedList<>();
    private Map<Class<? extends Event>, LinkedList<Event>> eventsPerType = new HashMap<>();

    private LinkedList<Attendee> attendees = new LinkedList<>();

    public LinkedList<Event> getEvents() { return events; };

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

    public static void main(String args[]) {
        EventsManager manager = new EventsManager();

        Student student = new Student("estudante", 1);
        Professor professor = new Professor("professor", 1);
        ExternalAttendee external = new ExternalAttendee("externo", 1);
        manager.addAttendee(student);
        manager.addAttendee(professor);
        manager.addAttendee(external);

        manager.addEvent(new Course("curso", LocalDate.now(), "ssa", 25, "curso", professor, 100));
        manager.addEvent(new Talk("palestra", LocalDate.now(), "ssa", 20, "palestra", 50, external));
        manager.addEvent(new Workshop("workshop", LocalDate.now(), "ssa", 65, "workshop", 45, external));

        System.out.println(manager.getEventsReport());
    }
}
