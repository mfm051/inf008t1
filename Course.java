import java.util.Date;

public class Course extends Event {
    Professor lecturer;
    int lengthInMinutes;

    public Course(String title, Date date, String location, int capacity, String description, Professor lecturer, int lengthInMinutes)
    {
        super(title, date, location, capacity, description);

        this.lecturer = lecturer;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public String getAttendeeCertificate(Attendee attendee) {
        if (!getAttendees().contains(attendee))
            return "Participante não cadastrado no curso";

        return "Informamos que " + attendee.getFullInfo() + " participou do curso " + getTitle() + 
                " com carga horária de " + getReadableWorkload(attendee) + " em " + getReadableDate();
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação como ouvinte a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student && getRemainingCapacity() > 0;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return lengthInMinutes;
    }
}
