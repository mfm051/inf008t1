import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class AcademicFair extends Event {
    private HashSet<Talk> talks;
    private HashSet<Workshop> workshops;

    private HashMap<Attendee, HashSet<Talk>> attendeesTalks;
    private HashMap<Attendee, HashSet<Workshop>> attendeesWorkShops;

    public AcademicFair(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description);
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        int totalMinAttendee = 0;

        HashSet<Talk> attendeeTalks = getAttendeeTalks(attendee);
        HashSet<Workshop> attendeeWorkshops = getAttendeeWorkshops(attendee);

        for (Talk talk : attendeeTalks) {
            totalMinAttendee += talk.getAttendeeWorkloadInMinutes(attendee);
        }

        for (Workshop workshop : attendeeWorkshops) {
            totalMinAttendee += workshop.getAttendeeWorkloadInMinutes(attendee);
        }

        return totalMinAttendee;
    }

    private HashSet<Talk> getAttendeeTalks(Attendee attendee) { 
        return attendeesTalks.get(attendee); 
    };

    private HashSet<Workshop> getAttendeeWorkshops(Attendee attendee) { 
        return attendeesWorkShops.get(attendee); 
    };

    @Override
    protected int getEventWorkloadInMinutes() {
        int total = 0;

        for (Talk talk : talks) {
            total += talk.getEventWorkloadInMinutes();
        }

        for (Workshop workshop : workshops) {
            total += workshop.getEventWorkloadInMinutes();
        }

        return total;
    }
}
