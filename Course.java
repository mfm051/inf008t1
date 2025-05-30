import java.util.Date;

public class Course extends Event {
    public Course(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description, description, capacity);
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }

    @Override
    protected String getEventType() { return "Curso"; };

    public static void main(String[] args) {
        Course c = new Course("a", new Date(), "ssa", 20, "legal");
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
