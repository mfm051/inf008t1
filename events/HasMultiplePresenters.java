package events;

import attendees.Attendee;

public interface HasMultiplePresenters {
    public void addPresenter(Attendee presenter) throws Exception;
}
