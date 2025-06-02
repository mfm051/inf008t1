import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AcademicFair extends Event {
    // Uma feira acadêmica é composta por diversas atividades
    // que podem ser vistas como eventos internos da feira
    private HashMap<Event, HashSet<Attendee>> attendeesPerActivity;

    public AcademicFair(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description);
        
        attendeesPerActivity = new HashMap<Event, HashSet<Attendee>>();
    }

    public void addActivity(Event activity) throws Exception {
        if (activity instanceof AcademicFair)
            throw new Exception("É uma feira recursiva?");

        // A atividade deve ser criada e permitir a adição de participantes a ela
        attendeesPerActivity.put(activity, new HashSet<Attendee>());
    }

    public void registerAttendeeInActivity(Attendee attendee, Event activity) throws Exception {
        if (!getAttendees().contains(attendee))
            throw new Exception("Participante não registrado na Feira");

        if (!getActivities().contains(activity))
            throw new Exception("Atividade não cadastrada na feira");
            
        attendeesPerActivity.get(activity).add(attendee);
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        int totalMinAttendee = 0;

        for (Event activity : getActivities()) {
            HashSet<Attendee> attendees = attendeesPerActivity.get(activity);

            if (attendees.contains(attendee))
                totalMinAttendee += activity.getAttendeeWorkloadInMinutes(attendee);
        }

        return totalMinAttendee;
    }

    private Set<Event> getActivities() { return attendeesPerActivity.keySet(); };

    public static void main(String[] args) {
        Professor p = new Professor("Vandro", 1);
        Workshop w = new Workshop("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 200, p);
        Talk t = new Talk("Java é mó legal", new Date(), "Salvador, BA", 20, "Palestra sobre javinha", 50, p);
        Attendee a = new Student("teste", 1);

        AcademicFair af = new AcademicFair("Feira de linguadens", new Date(), "Salvador, BA", 20, "Feira de linguagens de programação");

        try {
            af.addAttendee(a);
            af.addActivity(t);
            af.addActivity(w);

            af.registerAttendeeInActivity(a, t);
            af.registerAttendeeInActivity(a, w);
            System.out.println(af.getAttendeeCertificate(a));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
