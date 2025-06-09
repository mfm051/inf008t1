package events;

import java.util.List;

import attendees.Attendee;

@FunctionalInterface
public interface EventBuilderFunction {
    Event build(List<Attendee> attendees) throws Exception;
}
