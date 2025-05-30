public class Attendee {
    private String name;

    public Attendee(String name) {
        this.name = name;
    }
    
    public void attendEvent(Event event) {
        try {
            event.addAttendee(this);
        }
        catch (Exception ex)
        {
            System.out.println("Cadastro inválido: " + ex);
        }
    }

    public String getName() { return name; };
}
