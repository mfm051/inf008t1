package attendees;

public class ExternalAttendee implements Attendee {
    private String name;
    private int externalId;

    public ExternalAttendee(String name, int externalId) {
        this.name = name;
        this.externalId = externalId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullInfo() {
        return name + " " + "CPF" + " " + externalId;
    }
}
