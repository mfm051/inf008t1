package attendees;

public class Professor implements Attendee {
    String name;
    int professorId;

    public Professor(String name, int professorId) {
        this.name = name;
        this.professorId = professorId;
    }

    @Override
    public String getFullInfo() {
        return "professor" + " " + name + " " + "matrícula" + " " + professorId;
    }
}
