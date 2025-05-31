import java.util.Date;

public class Talk extends Event {
    public Talk(String title, Date date, String location, int capacity, String description, int durationInMin)
    {
        super(title, date, location, capacity, description, durationInMin);
    }
}
