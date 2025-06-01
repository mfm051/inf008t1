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

        attendeesTalks = new HashMap<Attendee, HashSet<Talk>>();
        attendeesWorkShops = new HashMap<Attendee, HashSet<Workshop>>();
    }

    public void addTalk(Talk talk) {
        talks.add(talk);
    }

    public void addWorkshop(Workshop workshop) {
        workshops.add(workshop);
    }

    public void registerAttendeeInEvent(Attendee attendee, Event event) throws Exception {
        // Garante que exista um espaço para adição de eventos do participante
        initializeAttendeeEvents(attendee);

        if (event instanceof Talk) {
            attendeesTalks.get(attendee).add((Talk)event);
            return;
        }

        if (event instanceof Workshop) {
            attendeesWorkShops.get(attendee).add((Workshop)event);
            return;
        }

        throw new Exception("Evento não cadastrado no Workshop");
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        int totalMinAttendee = 0;

        HashSet<Talk> attendeeTalks = attendeesTalks.get(attendee);
        HashSet<Workshop> attendeeWorkshops = attendeesWorkShops.get(attendee);

        for (Talk talk : attendeeTalks) {
            totalMinAttendee += talk.getAttendeeWorkloadInMinutes(attendee);
        }

        for (Workshop workshop : attendeeWorkshops) {
            totalMinAttendee += workshop.getAttendeeWorkloadInMinutes(attendee);
        }

        return totalMinAttendee;
    }

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

    private void initializeAttendeeEvents(Attendee attendee) throws Exception {
        if (!getAttendees().contains(attendee)) {
            addAttendee(attendee);
        }

        attendeesTalks.putIfAbsent(attendee, new HashSet<Talk>());
        attendeesWorkShops.putIfAbsent(attendee, new HashSet<Workshop>());
    }

    public static void main(String[] args) {
        Professor p = new Professor("Vandro", 1);
        Workshop w = new Workshop("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 200, p);
        Talk t = new Talk("Java é mó legal", new Date(), "Salvador, BA", 20, "Palestra sobre javinha", 50, p);
        Attendee a = new Student("teste", 1);

        AcademicFair af = new AcademicFair("Feira de linguadens", new Date(), "Salvador, BA", 20, "Feira de linguagens de programação");

        try {
            af.registerAttendeeInEvent(a, t);
            af.registerAttendeeInEvent(a, w);
            System.out.println(af.getAttendeeCertificate(a));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
