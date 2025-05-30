import java.util.Date;

public class Workshop extends Event {
    public Workshop(String title, Date date, String location, int capacity, String description)
    {
        super(title, date, location, capacity, description, description, capacity);
    }
}
