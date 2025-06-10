package attendees;

public class Student implements Attendee {
    String name;
    int studentId;

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullInfo() {
        return "estudante" + " " + name + " " + "matrícula" + " " + studentId;
    }
}
