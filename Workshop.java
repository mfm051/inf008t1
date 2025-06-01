import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class Workshop extends Event {
    // Assume-se que um workshop é um evento mais abrangente
    // podendo o instrutor não se limitar a um professor
    private Attendee instructor;

    public Workshop(String title, Date date, String location, int capacity, String description, int lengthInMin, Attendee instructor) {
        super(title, date, location, capacity, description, lengthInMin);
        this.instructor = instructor;
    }

    public static void main(String[] args) {
        Attendee p = new Student("Vandro", 1);
        Workshop c = new Workshop("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 65, p);
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
