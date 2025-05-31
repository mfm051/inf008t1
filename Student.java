public class Student implements Attendee {
    String name;
    int studentId;

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getAttendeeDetails() {
        return "estudante" + " " + name + " " + "matrícula" + " " + studentId;
    }
}
