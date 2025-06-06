import java.util.Date;

public class Workshop extends Event {
    // Um workshop é um evento mais abrangente
    // podendo o instrutor ser qualquer participante com conhecimento no tema
    private Attendee instructor;

    int lengthInMinutes;

    public Workshop(String title, Date date, String location, int capacity, String description, int lengthInMinutes,
            Attendee instructor) {
        super(title, date, location, capacity, description);

        this.lengthInMinutes = lengthInMinutes;
        this.instructor = instructor;
    }

    @Override
    protected boolean isPresenter(Attendee attendee) {
        return attendee == instructor;
    }

    @Override
    protected String getPresenterCertificate(Attendee attendee) {
        return "Certificamos que " + attendee.getFullInfo() + " foi instrutor no workshop " + getTitle() + " em " + getReadableDate();
    }

    @Override
    protected String getParticipationCertificate(Attendee attendee) {
        return "Informamos que o participante " + attendee.getFullInfo() + " participou do workshop "
                + getTitle() + " com uma carga horária total de " + getReadableWorkload(attendee);
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return lengthInMinutes;
    }

    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return getRemainingCapacity() > 0;
    }
}
