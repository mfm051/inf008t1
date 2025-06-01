import java.util.Date;

public class Course extends Event {
    Professor lecturer;

    public Course(String title, Date date, String location, int capacity, String description, int lengthInMin, Professor lecturer)
    {
        super(title, date, location, capacity, description, lengthInMin);
        this.lecturer = lecturer;
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }
}
