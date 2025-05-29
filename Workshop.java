import java.util.Date;

public class Workshop extends Event {
    public Workshop(String title, Date date, String place, int capacity, String description)
    {
        super(title, date, place, capacity, description, description, capacity);
    }

    public String getAttendeeCertificate(String attendeeName) {
        return "";
    }
}
