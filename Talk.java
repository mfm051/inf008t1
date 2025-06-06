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
    protected boolean isPresenter(Attendee attendee) {
        return attendee == speaker;
    }

    @Override
    protected String getPresenterCertificate(Attendee attendee) {
        return "Certificamos que " + attendee.getFullInfo() + " apresentou a palestra " + getTitle() + " em " + getReadableDate();
    }

    @Override
    protected String getParticipationCertificate(Attendee attendee) {
        return "Informamos que o participante " + attendee.getFullInfo() + " participou da palestra "
                + getTitle() + " com uma carga horária total de " + getReadableWorkload(attendee);
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
