package events;

import attendees.Attendee;

public class EventCertificate {
    private Attendee attendee;
    private Event event;

    public EventCertificate(Attendee attendee, Event event) {
        this.attendee = attendee;
        this.event = event;
    }

    public String getMessage() {
        return "Certificamos a participação do(a) " + attendee.getFullInfo()
                + (event.isPresenter(attendee) ? " como apresentador" : "") +
                " no evento " + event.getTitle()
                + " realizado em " + event.getLocation() + " em " + event.getReadableDate() +
                " com uma carga horária de " + event.getReadableWorkload(attendee);
    }
}