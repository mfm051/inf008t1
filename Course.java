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

    // Os cursos se distinguem dos demais eventos por restringirem a participação como ouvinte a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return getEventWorkloadInMinutes();
    }

    @Override
    protected int getEventWorkloadInMinutes() { 
        return lengthInMinutes;
    }
}
