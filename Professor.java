public class Professor implements Attendee {
    String name;
    int professorId;

    public Professor(String name, int professorId) {
        this.name = name;
        this.professorId = professorId;
    }

    public String getAttendeeDetails() {
        return "professor" + " " + name + " " + "matrícula" + " " + professorId;
    }
}
