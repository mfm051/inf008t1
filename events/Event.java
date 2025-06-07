package events;

import java.util.Date;
import java.util.HashSet;

import attendees.Attendee;

abstract class Event {
    private String title;
    private Date date;
    private String location;
    private int capacity;
    private String description;
    private HashSet<Attendee> attendees;

    public Event(String title, Date date, String location, int capacity, String description) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        attendees = new HashSet<Attendee>();
    }

    public String getTitle() {
        return title;
    };

    public String getReadableDate() {
        return date.toString();
    };

    public String getLocation() {
        return location;
    }

    public HashSet<Attendee> getAttendees() {
        return attendees;
    };

    public void addAttendee(Attendee attendee) throws Exception {
        if (!acceptsAttendee(attendee))
            throw new Exception("Participante não permitido no evento");

        attendees.add(attendee);
    }

    public String getAttendeeCertificateMessage(Attendee attendee) {
        return new EventCertificate(attendee, this).getMessage();
    }

    public boolean isAttendee(Attendee attendee) {
        return attendees.contains(attendee);
    }

    public abstract boolean isPresenter(Attendee attendee);

    protected abstract int getAttendeeWorkloadInMinutes(Attendee attendee);

    protected abstract boolean acceptsAttendee(Attendee attendee);

    protected int getRemainingCapacity() {
        return capacity - attendees.size();
    }

    protected String getReadableWorkload(Attendee attendee) {
        int workloadInMinutes = getAttendeeWorkloadInMinutes(attendee);

        int hours = workloadInMinutes / 60;
        int mins = workloadInMinutes % 60;

        String workload = "";

        if (hours > 0)
            workload += hours + "h";
        if (mins > 0)
            workload += mins + "m";

        return workload;
    }
}