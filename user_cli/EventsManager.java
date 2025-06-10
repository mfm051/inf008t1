package user_cli;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import attendees.*;
import events.*;

public class EventsManager {
    private LinkedList<Event> events = new LinkedList<>();
    private Map<Class<? extends Event>, LinkedList<Event>> eventsPerType = new HashMap<>();
    private LinkedList<Attendee> attendees = new LinkedList<>();
    private Map<Integer, Runnable> menuActions = new HashMap<>();

    private static final Scanner userInput = new Scanner(System.in);

    public EventsManager() {
        menuActions.put(MenuOption.EXIT.code, () -> {
        });
        menuActions.put(MenuOption.REGISTER_EVENT.code, () -> registerEvent());
        menuActions.put(MenuOption.REGISTER_ATTENDEE.code, () -> registerAttendee());
        menuActions.put(MenuOption.REGISTER_ATTENDEE_IN_EVENT.code, () -> registerAttendeeInEvent());
        menuActions.put(MenuOption.SHOW_CERTIFICATE.code, () -> showAttendeeCertificate());
        menuActions.put(MenuOption.SHOW_REPORT.code, () -> showEventsReport());
    }

    public boolean runUserAction() {
        MenuOption.showMenu();
        System.out.print("Choose an option: ");
        int action = MenuOption.EXIT.code;

        try {
            action = Integer.parseInt(userInput.nextLine());

            if (action == MenuOption.EXIT.code)
                return false;
        } catch (Exception ex) {
            System.out.println("Não foi possível identificar o número. Tente novamente");
            return true;
        }

        Runnable userAction = menuActions.get(action);
        if (userAction == null) {
            System.out.println("Opção não reconhecida");
            return false;
        }

        userAction.run();
        return true;
    }

    public void registerEvent() {
        try {
            Event event = EventBuilder.createEventFromUserInput(attendees);
            addEvent(event);
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar evento");
            System.out.println(ex.getMessage());
        }
    }

    public void registerAttendee() {
        try {
            Attendee attendee = AttendeeBuilder.createAttendeeFromUserInput();
            addAttendee(attendee);
        } catch (Exception ex) {
            System.out.println("Não foi possível cadastrar participante");
            System.out.println(ex.getMessage());
        }
    }

    public void registerAttendeeInEvent() {
        System.out.print("Informe o nome do participante: ");
        Attendee attendee = findAttendeeByName(userInput.nextLine());

        if (attendee == null) {
            System.out.println("Participante não encontrado");
            return;
        }

        System.out.print("Informe o título do evento: ");
        Event event = findEventByTitle(userInput.nextLine());

        if (event == null) {
            System.out.println("Evento não encontrado");
            return;
        }

        String answer = "n";
        if (event instanceof HasMultiplePresenters) {
            System.out.println("Deseja cadastrar como apresentador? [s/n]");
            answer = userInput.nextLine().toLowerCase();
        }

        try {
            if (answer.startsWith("n"))
                event.addAttendee(attendee);
            else
                ((HasMultiplePresenters) event).addPresenter(attendee);
        } catch (Exception ex) {
            System.out.println("Não foi possível registrar participante");
            System.out.println(ex.getMessage());
        }
    }

    public void showAttendeeCertificate() {
        System.out.print("Informe o nome do participante: ");
        Attendee attendee = findAttendeeByName(userInput.nextLine());

        if (attendee == null) {
            System.out.println("Participante não encontrado");
            return;
        }

        System.out.print("Informe o título do evento: ");
        Event event = findEventByTitle(userInput.nextLine());

        if (event == null) {
            System.out.println("Evento não encontrado");
            return;
        }

        System.out.println(event.getAttendeeCertificateMessage(attendee));
    }

    private Attendee findAttendeeByName(String name) {
        for (Attendee attendee : attendees) {
            if (attendee.getName().toLowerCase() == name.toLowerCase())
                return attendee;
        }

        return null;
    }

    private Event findEventByTitle(String name) {
        for (Event event : events) {
            if (event.getTitle().toLowerCase() == name.toLowerCase())
                return event;
        }

        return null;
    }

    private void addEvent(Event event) {
        events.add(event);

        Class<? extends Event> eventType = event.getClass();
        eventsPerType
                .computeIfAbsent(eventType, k -> new LinkedList<Event>())
                .add(event);
    }

    private void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    private void showEventsReport() {
        Class<? extends Event> eventType;
        LinkedList<Event> eventsRegistered;

        String report = "";

        for (Map.Entry<Class<? extends Event>, LinkedList<Event>> typeEvents : eventsPerType.entrySet()) {
            eventType = typeEvents.getKey();
            eventsRegistered = typeEvents.getValue();

            report += eventType.getSimpleName();
            report += "\n";

            for (Event event : eventsRegistered) {
                report += event.getTitle() + " " + event.getReadableDate();
                report += "\n";
            }

            report += "\n\n";
        }

        System.out.println(report);
    }

    public static void main(String[] args) {
        EventsManager manager = new EventsManager();

        while (manager.runUserAction()) { }
    }
}
