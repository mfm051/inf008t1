package events;

import java.time.LocalDate;

import attendees.Attendee;
import attendees.Student;
import attendees.Professor;

public class Course extends Event {
    Professor lecturer;
    int lengthInMinutes;

    public Course(String title, LocalDate date, String location, int capacity, String description, Professor lecturer,
            int lengthInMinutes) {
        super(title, date, location, capacity, description);

        this.lecturer = lecturer;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public boolean isPresenter(Attendee attendee) {
        return attendee == lecturer;
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
