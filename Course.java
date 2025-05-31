import java.util.Date;

public class Course extends Event {
    Professor lecturer;

    public Course(String title, Date date, String location, int capacity, String description, int durationInMin, Professor lecturer)
    {
        super(title, date, location, capacity, description, durationInMin);
        this.lecturer = lecturer;
    }

    // Os cursos se distinguem dos demais eventos por restringirem a participação a alunos
    @Override
    protected boolean acceptsAttendee(Attendee attendee) {
        return attendee instanceof Student;
    }

    public static void main(String[] args) {
        Professor p = new Professor("Vandro", 1);
        Course c = new Course("Introdução ao java", new Date(), "Salvador, BA", 20, "legal", 65, p);
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
