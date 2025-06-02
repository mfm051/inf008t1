public class ExternalAttendee implements Attendee {
    private String name;
    private int externalId;

    public ExternalAttendee(int externalId) {
        this.externalId = externalId;
    }

    @Override
    public String getFullInfo() {
        return name + " " + "CPF" + " " + externalId;
    }
}
