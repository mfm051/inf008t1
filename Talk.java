import java.util.Date;

public class Talk extends Event {
    // Um palestrante pode ser qualquer participante do evento
    private Attendee speaker;

    private int lengthInMinutes;

    public Talk(String title, Date date, String location, int capacity, String description, int lengthInMinutes,
            Attendee speaker) {
        super(title, date, location, capacity, description);

        this.lengthInMinutes = lengthInMinutes;
        this.speaker = speaker;
    }

    @Override
    public String getAttendeeCertificate(Attendee attendee) {
        if (!getAttendees().contains(attendee))
            return "Participante não cadastrado na palestra";

        return "Informamos que " + attendee.getFullInfo() + " participou da palestra " + getTitle() +
                " com carga horária de " + getReadableWorkload(attendee) + " em " + getReadableDate();
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
