package events;

import java.time.LocalDate;

import attendees.Attendee;

public class Talk extends Event {
    // Um palestrante pode ser qualquer participante do evento
    private Attendee speaker;

    private int lengthInMinutes;

    public Talk(String title, LocalDate date, String location, int capacity, String description, int lengthInMinutes,
            Attendee speaker) {
        super(title, date, location, capacity, description);

        this.lengthInMinutes = lengthInMinutes;
        this.speaker = speaker;
    }

    @Override
    public boolean isPresenter(Attendee attendee) {
        return attendee == speaker;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return lengthInMinutes;
    };

    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return getRemainingCapacity() > 0;
    }
}
