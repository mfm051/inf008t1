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
        if (event.isAttendee(attendee))
            return getParticipationMessage();

        if (event.isPresenter(attendee))
            return getPresenterMessage();

        return "Participante não registrado no evento";
    }

    public String getShortDescription() {
        return event.getTitle() + ": " + event.getReadableDate() + "(" + event.getReadableWorkload(attendee) + ")";
    }

    private String getParticipationMessage() {
        return "Certificamos que " + attendee.getFullInfo() + " participou do evento " + event.getTitle()
                + " realizado em " + event.getLocation() + " em " + event.getReadableDate() +
                " com uma carga horária de " + event.getReadableWorkload(attendee);
    }

    private String getPresenterMessage() {
        return "Certificamos que " + attendee.getFullInfo() + " foi apresentador no evento " + event.getTitle()
                + " realizado em " + event.getLocation() + " em " + event.getReadableDate();
    }
}