import java.util.Date;

public class Workshop extends Event {
    public Workshop(String title, Date date, String location, int capacity, String description, int durationInMin)
    {
        super(title, date, location, capacity, description, durationInMin);
    }
}
