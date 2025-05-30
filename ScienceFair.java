import java.util.Date;

public class ScienceFair extends Event {
    public ScienceFair(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description, description, capacity);
    }

    @Override
    protected getEventType() { return "Feira acadêmica" }
}
