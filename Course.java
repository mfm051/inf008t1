import java.util.Date;

public class Course extends Event {
    public Course(String title, Date date, String place, int capacity, String description)
    {
        super(title, date, place, capacity, description, description, capacity);
    }

    public String getAttendeeCertificate(String attendeeName) {
        return "";
    }

    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }
}
