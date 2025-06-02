import java.util.Date;
import java.util.HashSet;

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

        return "Informamos que o participante " + attendee.getAttendeeDetails() + 
                " participou de " +  title + 
                " com uma carga horária de " + 
                getReadableWorkload(attendee) +
                " realizado em " + location + 
                " na data " + date.toString();
    }

    public HashSet<Attendee> getAttendees() { return attendees; };

    protected abstract int getAttendeeWorkloadInMinutes(Attendee attendee);

    // "Evento" aceita qualquer participante por padrão
    // Caso necessário, modificar nos eventos específicos
    protected boolean acceptsAttendee(Attendee attendee) { return true; }

    private String getReadableWorkload(Attendee attendee) {
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

    private int remainingCapacity() { return capacity - attendees.size(); }
}