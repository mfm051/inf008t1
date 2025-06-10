package user_cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import attendees.Attendee;
import events.Event;
import events.HasMultiplePresenters;
import builders.AttendeeBuilder;
import builders.EventBuilder;
import managers.AttendeesManager;
import managers.EventsManager;

public class CommandHandler {
    private Scanner userInput;
    private EventsManager eventsManager = new EventsManager();
    private AttendeesManager attendeesManager = new AttendeesManager();
    private Map<Integer, Runnable> menuActions = new HashMap<>();

    public CommandHandler(Scanner userInput) {
        this.userInput = userInput;

        menuActions.put(MenuOption.EXIT.code, () -> {
        });
        menuActions.put(MenuOption.REGISTER_EVENT.code, () -> registerEvent());
        menuActions.put(MenuOption.REGISTER_ATTENDEE.code, () -> registerAttendee());
        menuActions.put(MenuOption.REGISTER_ATTENDEE_IN_EVENT.code, () -> registerAttendeeInEvent());
        menuActions.put(MenuOption.SHOW_CERTIFICATE.code, () -> showAttendeeCertificate());
        menuActions.put(MenuOption.SHOW_REPORT.code, () -> showEventsReport());
    }

    public Runnable getActionByCode(int code) {
        return menuActions.get(code);
    }

    public void registerEvent() {
        try {
            Event event = EventBuilder.createEventFromUserInput(attendeesManager.getAttendees());
            eventsManager.add(event);
        } catch (Exception ex) {
            System.out.print("\n\nNão foi possível cadastrar evento\n\n");
            System.out.println(ex.getMessage());
        }
    }

    public void registerAttendee() {
        try {
            Attendee attendee = AttendeeBuilder.createAttendeeFromUserInput();
            attendeesManager.add(attendee);
        } catch (Exception ex) {
            System.out.print("\n\nNão foi possível cadastrar participante\n\n");
            System.out.println(ex.getMessage());
        }
    }

    public void registerAttendeeInEvent() {
        System.out.print("Informe o nome do participante: ");
        Attendee attendee = attendeesManager.findByName(userInput.nextLine());

        if (attendee == null) {
            System.out.print("\n\nParticipante não encontrado\n\n");
            return;
        }

        System.out.print("Informe o título do evento: ");
        Event event = eventsManager.findByTitle(userInput.nextLine());

        if (event == null) {
            System.out.print("\n\nEvento não encontrado\n\n");
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
            System.out.print("\n\nNão foi possível registrar participante\n\n");
            System.out.println(ex.getMessage());
        }
    }

    public void showAttendeeCertificate() {
        System.out.print("Informe o nome do participante: ");
        Attendee attendee = attendeesManager.findByName(userInput.nextLine());

        if (attendee == null) {
            System.out.print("\n\nParticipante não encontrado\n\n");
            return;
        }

        System.out.print("Informe o título do evento: ");
        Event event = eventsManager.findByTitle(userInput.nextLine());

        if (event == null) {
            System.out.print("\n\nEvento não encontrado\n\n");
            return;
        }

        System.out.println(event.getAttendeeCertificateMessage(attendee));
    }

    public void showEventsReport() {
        eventsManager.showReport();
    }
}
