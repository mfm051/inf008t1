import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class Workshop extends Event {
    // Um workshop é um evento mais abrangente
    // podendo o instrutor ser qualquer participante com conhecimento no tema
    private Attendee instructor;

    int lengthInMinutes;

    public Workshop(String title, Date date, String location, int capacity, String description, int lengthInMinutes, Attendee instructor) {
        super(title, date, location, capacity, description);

        this.lengthInMinutes = lengthInMinutes;
        this.instructor = instructor;
    }

    @Override
    protected int getAttendeeWorkloadInMinutes(Attendee attendee) {
        return getEventWorkloadInMinutes();
    }

    @Override
    protected int getEventWorkloadInMinutes() {
        return lengthInMinutes;
    }
}
