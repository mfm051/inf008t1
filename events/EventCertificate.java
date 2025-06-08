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
        if (event.isPresenter(attendee))
            return getPresenterMessage();

        if (event.isAttendee(attendee))
            return getParticipationMessage();

        return "Participante não registrado no evento";
    }

    private String getParticipationMessage() {
        return "Certificamos a participação do(a) " + attendee.getFullInfo() + " no evento " + event.getTitle()
                + " realizado em " + event.getLocation() + " em " + event.getReadableDate() +
                " com uma carga horária de " + event.getReadableWorkload(attendee);
    }

    private String getPresenterMessage() {
        return "Certificamos a participação do(a) " + attendee.getFullInfo() + " como apresentador no evento " + event.getTitle()
                + " realizado em " + event.getLocation() + " em " + event.getReadableDate() +
                " com uma carga horária de " + event.getReadableWorkload(attendee);
    }
}