package events;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import attendees.Attendee;
import attendees.Student;
import attendees.Professor;

public class AcademicFair extends Event {
    // Uma feira acadêmica é composta por diversas atividades
    // que podem ser vistas como eventos internos da feira
    private HashMap<Event, HashSet<Attendee>> attendeesPerActivity;

    public AcademicFair(String title, LocalDate date, String location, int capacity, String description,
            HashSet<Event> activities) throws Exception {
        super(title, date, location, capacity, description);

        attendeesPerActivity = new HashMap<Event, HashSet<Attendee>>();
        try {
            setActivities(activities);
        } catch (Exception e) {
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
    public String getAttendeeCertificateMessage(Attendee attendee) {
        String generalCertificateMessage = new EventCertificate(attendee, this).getMessage();
        String activitiesDetailsMessage = "";

        Set<Event> activities = getAtendeeActivities(attendee);
        if (!activities.isEmpty()) {
            activitiesDetailsMessage += "Relatório das atividades:\n";

            for (Event activity : activities) {
                activitiesDetailsMessage += new EventCertificate(attendee, activity).getShortDescription();
                activitiesDetailsMessage += "\n";
            }    
        }

        return generalCertificateMessage + "\n" + activitiesDetailsMessage;
    }

    @Override
    public boolean isPresenter(Attendee attendee) {
        return getPresenterActivity(attendee) != null;
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

    private Event getPresenterActivity(Attendee attendee) {
        for (Event activity : getActivities()) {
            if (activity.isPresenter(attendee))
                return activity;
        }

        return null;
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

        Workshop w = new Workshop("Introdução ao java", LocalDate.now(), "Salvador, BA", 20, "legal", 200, p);
        Talk t = new Talk("Java vs C#", LocalDate.now(), "Salvador, BA", 20, "Palestra sobre javinha", 50, p);
        HashSet<Event> activities = new HashSet<Event>();
        activities.add(w);
        activities.add(t);

        try {
            AcademicFair af = new AcademicFair("Feira de linguagens", LocalDate.now(), "Salvador, BA", 20,
                    "Feira de linguagens de programação", activities);
            af.addAttendee(a);

            af.registerAttendeeInActivity(a, t);
            af.registerAttendeeInActivity(a, w);

            System.out.println(af.getAttendeeCertificateMessage(a));
            System.out.println(af.getAttendeeCertificateMessage(p));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
