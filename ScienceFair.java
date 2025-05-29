import java.util.Date;

public class ScienceFair extends Event {
    public ScienceFair(String title, Date date, String place, int capacity, String description)
    {
        super(title, date, place, capacity, description, description, capacity);
    }

    public String getAttendeeCertificate(String attendeeName) {
        return "";
    }
}
