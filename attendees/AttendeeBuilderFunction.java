package attendees;

@FunctionalInterface
public interface AttendeeBuilderFunction {
    Attendee build() throws Exception;
}
