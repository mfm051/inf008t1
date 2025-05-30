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

    public void addAttendee(Attendee attendee) throws Exception {
        if (remainingCapacity() <= 0)
            throw new Exception("Evento lotado");

        if (!acceptsAttendee(attendee))
            throw new Exception("Participante não permitido no evento");

        attendees.add(attendee);
    }

    public String getAttendeeCertificate(Attendee attendee) throws Exception {
        if (!attendees.contains(attendee))
            throw new Exception("Participante não cadastrado no evento");

        return "Informamos que o participante " + attendee.getName() + 
                " participou do " + getEventType() + " " + title + " em " + date.toString();
    }

    protected int remainingCapacity() { return capacity - attendees.size(); }

    // Aceita qualquer participante por padrão
    protected boolean acceptsAttendee(Attendee attendee) { return true; }

    protected String getEventType() { return "Evento"; };
}