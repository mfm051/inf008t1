package builders;

import java.util.List;

import attendees.Attendee;
import events.Event;

@FunctionalInterface
public interface EventBuilderFunction {
    Event build(List<Attendee> attendees) throws Exception;
}
