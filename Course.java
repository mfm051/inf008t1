import java.util.Date;

public class Course extends Event {
    public Course(String title, Date date, String location, int capacity, String description, int durationInMin)
    {
        super(title, date, location, capacity, description, durationInMin);
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }

    public static void main(String[] args) {
        Course c = new Course("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 65);
        Student a = new Student("teste");

        try {
            c.addAttendee(a);
            System.out.println(c.getAttendeeCertificate(a));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
