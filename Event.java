import java.util.Date;
import java.util.HashSet;

public class Event {
    private String title;
    private Date date;
    private String place;
    private int capacity;
    private String description;
    private HashSet<Attendee> attendees;

    public Event(String title, Date date, String place, int capacity, String description) {
        this.title = title;
        this.date = date;
        this.place = place;
        this.capacity = capacity;
        this.description = description;
        attendees = new HashSet<Attendee>();
    }

    public void AddAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public String GenerateCertificate(/* Attendee */) {
        return "Stub";
    }
}