import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AcademicFair extends Event {
    // Uma feira acadêmica é composta por diversas atividades
    // que podem ser vistas como eventos internos da feira
    private HashMap<Event, HashSet<Attendee>> attendeesPerActivity;

    public AcademicFair(String title, Date date, String location, int capacity, String description,
            HashSet<Event> activities) throws Exception {
        super(title, date, location, capacity, description);

        attendeesPerActivity = new HashMap<Event, HashSet<Attendee>>();
        try {
            setActivities(activities);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Não foi possível cadastrar feira");
        }
    }

    public void registerAttendeeInActivity(Attendee attendee, Event activity) throws Exception {
        if (!getAttendees().contains(attendee))
            throw new Exception("Participante não registrado na Feira");

        if (!getActivities().contains(activity))
            throw new Exception("Atividade não cadastrada na feira");

        attendeesPerActivity.get(activity).add(attendee);
    }

    @Override
    public String getAttendeeCertificate(Attendee attendee) {
        if (!getAttendees().contains(attendee))
            return "Participante não cadastrado na feira acadêmica";

        String basicMessage = super.getAttendeeCertificate(attendee);

        String activitiesDetailsMessage = "Relatório das atividades:\n";

        for (Event activity : getAtendeeActivities(attendee)) {
            activitiesDetailsMessage += activity.getTitle() + ": " + activity.getReadableDate() + " ("
                    + activity.getReadableWorkload(attendee) + ")";
            activitiesDetailsMessage += "\n";
        }

        return basicMessage + "\n" + activitiesDetailsMessage;
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

    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return getRemainingCapacity() > 0;
    }

    private Set<Event> getActivities() {
        return attendeesPerActivity.keySet();
    };

    private Set<Event> getAtendeeActivities(Attendee attendee) {
        HashSet<Event> activities = new HashSet<Event>();

        for (Event activity : getActivities()) {
            HashSet<Attendee> attendees = attendeesPerActivity.get(activity);

            if (attendees.contains(attendee))
                activities.add(activity);
        }

        return activities;
    }

    private void setActivities(HashSet<Event> activities) throws Exception {
        if (activities == null || activities.size() == 0)
            throw new Exception("A feira deve conter ao menos uma atividade registrada");

        for (Event activity : activities) {
            if (activity instanceof AcademicFair)
                throw new Exception("Uma feira acadêmica não pode conter outra feira");

            attendeesPerActivity.put(activity, new HashSet<Attendee>());
        }
    }

    public static void main(String[] args) {
        Professor p = new Professor("Vandro", 1);
        Attendee a = new Student("teste", 1);

        Workshop w = new Workshop("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 200, p);
        Talk t = new Talk("Java vs C#", new Date(), "Salvador, BA", 20, "Palestra sobre javinha", 50, p);
        HashSet<Event> activities = new HashSet<Event>();
        activities.add(w);
        activities.add(t);

        try {
            AcademicFair af = new AcademicFair("Feira de linguagens", new Date(), "Salvador, BA", 20,
                "Feira de linguagens de programação", activities);
            af.addAttendee(a);

            af.registerAttendeeInActivity(a, t);
            af.registerAttendeeInActivity(a, w);

            System.out.println(af.getAttendeeCertificate(a));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
