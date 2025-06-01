import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class Workshop extends Event {
    // Um workshop é um evento mais abrangente
    // podendo o instrutor ser qualquer participante com conhecimento no tema
    private Attendee instructor;

    int lengthInMinutes;

    public Workshop(String title, Date date, String location, int capacity, String description, int lengthInMinutes, Attendee instructor) {
        super(title, date, location, capacity, description);

        this.lengthInMinutes = lengthInMinutes;
        this.instructor = instructor;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return getEventWorkloadInMinutes();
    }

    @Override
    protected int getEventWorkloadInMinutes() {
        return lengthInMinutes;
    }

    public static void main(String[] args) {
        Attendee p = new Student("Vandro", 1);
        Workshop c = new Workshop("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 200, p);
        Student a = new Student("teste", 1);

        try {
            c.addAttendee(a);
            System.out.println(c.getAttendeeCertificate(a));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
