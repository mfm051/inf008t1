package events;

import java.time.LocalDate;
import java.util.LinkedList;

import attendees.Attendee;
import attendees.Student;
import attendees.Professor;

public class AcademicFair extends Event implements HasMultiplePresenters {
    // Uma feira acadêmica é composta por diversas atividades
    // Em que participantes podem ser ouvintes e apresentadores
    private LinkedList<Attendee> presenters;
    private int attendeeWorkloadInMinutes;
    private int presenterWorkloadInMinutes;

    public AcademicFair(String title, LocalDate date, String location, int capacity, String description,
            int attendeeWorkloadInMinutes, int presenterWorkloadInMinutes) {
        super(title, date, location, capacity, description);

        presenters = new LinkedList<Attendee>();
        this.attendeeWorkloadInMinutes = attendeeWorkloadInMinutes;
        this.presenterWorkloadInMinutes = presenterWorkloadInMinutes;
    }

    @Override
    public void addPresenter(Attendee attendee) throws Exception {
        // Um apresentador em uma feira é também um participante
        try {
            addAttendee(attendee);
        } catch (Exception ex) {
            throw ex;
        }

        presenters.add(attendee);
    }

    @Override
    public boolean isPresenter(Attendee attendee) {
        return presenters.contains(attendee);
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        if (isPresenter(attendee))
            return presenterWorkloadInMinutes;
        else
            return attendeeWorkloadInMinutes;
    }

    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return getRemainingCapacity() > 0;
    }

    public static void main(String[] args) {
        Professor p = new Professor("Vandro", 1);
        Attendee a = new Student("teste", 1);

        try {
            AcademicFair af = new AcademicFair("Feira de linguagens", LocalDate.now(), "Salvador, BA", 20,
                    "Feira de linguagens de programação", 240, 480);
            af.addAttendee(a);
            af.addPresenter(p);

            System.out.println(af.getAttendeeCertificateMessage(a));
            System.out.println(af.getAttendeeCertificateMessage(p));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
