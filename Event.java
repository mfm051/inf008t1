import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

abstract class Event {
    private String title;
    private Date date;
    private String location;
    private int capacity;
    private String description;
    private LinkedList<Attendee> attendees;
    private String theme;
    private int durationInMin;

    public Event(String title, Date date, String location, int capacity, String description, String theme, int durationInMin) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        attendees = new LinkedList<Attendee>();
        this.theme = theme;
        this.durationInMin = durationInMin;
    }

    public void AddAttendee(Attendee attendee) throws Exception {
        if (remainingCapacity() <= 0)
            throw new Exception("Evento lotado");

        if (!acceptsAttendee(attendee))
            throw new Exception("Participante não permitido no evento");

        attendees.add(attendee);
    }

    abstract String getAttendeeCertificate(String attendeeName);

    protected int remainingCapacity() { return capacity - attendees.size(); }

    protected Attendee findAttendeeByName(String attendeeName) {
        Attendee attendee = attendees.getFirst();
        ListIterator<Attendee> attendeesIterator = attendees.listIterator(0);

        while (attendee != null) {
            if (attendee.getName() == attendeeName) return attendee;

            attendee = attendeesIterator.next();
        }

        return attendee;
    }

    protected boolean acceptsAttendee(Attendee attendee) { return true; }
}