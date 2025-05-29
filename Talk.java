import java.util.Date;

public class Talk extends Event {
    public Talk(String title, Date date, String place, int capacity, String description)
    {
        super(title, date, place, capacity, description, description, capacity);
    }

    public String getAttendeeCertificate(String attendeeName) {
        return "";
    }
}
