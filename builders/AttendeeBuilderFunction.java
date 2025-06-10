package builders;

import attendees.Attendee;

@FunctionalInterface
public interface AttendeeBuilderFunction {
    Attendee build() throws Exception;
}
