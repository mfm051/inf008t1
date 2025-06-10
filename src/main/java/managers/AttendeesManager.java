package managers;

import java.util.LinkedList;
import java.util.List;

import attendees.*;

public class AttendeesManager {
    private LinkedList<Attendee> attendees = new LinkedList<>();

    public void add(Attendee attendee) {
        attendees.add(attendee);
    }
    
    public Attendee findByName(String name) {
        for (Attendee attendee : attendees) {
            if (attendee.getName().toLowerCase() == name.toLowerCase())
                return attendee;
        }

        return null;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }
}
