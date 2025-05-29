public class Attendee {
    private String name;
    
    public void attendEvent(Event event) {
        try {
            event.AddAttendee(this);
        }
        catch (Exception ex)
        {
            System.out.println("Cadastro inválido: " + ex);
        }
    }

    public String getName() { return name; };
}
