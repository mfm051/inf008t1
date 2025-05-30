import java.util.Date;

public class Talk extends Event {
    public Talk(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description, description, capacity);
    }
}
