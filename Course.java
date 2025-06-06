import java.util.Date;

public class Course extends Event {
    Professor lecturer;
    int lengthInMinutes;

    public Course(String title, Date date, String location, int capacity, String description, Professor lecturer,
            int lengthInMinutes) {
        super(title, date, location, capacity, description);

        this.lecturer = lecturer;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    protected boolean isPresenter(Attendee attendee) {
        return attendee == lecturer;
    }

    @Override
    protected String getPresenterCertificate(Attendee attendee) {
        return "Certificamos que " + attendee.getFullInfo() + " apresentou o curso " + getTitle() + " em " + getReadableDate();
    }

    @Override
    protected String getParticipationCertificate(Attendee attendee) {
        return "Informamos que o participante " + attendee.getFullInfo() + " participou do curso "
                + getTitle() + " com uma carga horária total de " + getReadableWorkload(attendee);
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação
    // como ouvinte a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student && getRemainingCapacity() > 0;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return lengthInMinutes;
    }
}
