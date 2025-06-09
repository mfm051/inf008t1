package events;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import attendees.Attendee;
import attendees.Professor;

public class UserEventBuilder {
    private static final Map<String, EventBuilderFunction> eventBuilders = new HashMap<>();
    private static final Scanner userInput = new Scanner(System.in);

    static {
        eventBuilders.put("feira acadêmica", UserEventBuilder::buildAcademicFair);
        eventBuilders.put("palestra", UserEventBuilder::buildTalk);
        eventBuilders.put("curso", UserEventBuilder::buildCourse);
        eventBuilders.put("workshop", UserEventBuilder::buildWorkshop);
    }

    public static Event createEventFromUserInput(List<Attendee> attendees) throws Exception {
        System.out.println("Eventos disponíveis: " + eventBuilders.keySet());
        System.out.print("Escolha um evento: ");
        String type = userInput.nextLine().trim().toLowerCase();

        EventBuilderFunction builder = eventBuilders.get(type);
        if (builder != null) {
            return builder.build(attendees);
        } else {
            throw new Exception("Tipo de evento inválido");
        }
    }

    private static Event buildAcademicFair(List<Attendee> attendees) throws Exception {
        String title = EventsInputsValidator.getTitle(userInput);
        LocalDate date = EventsInputsValidator.getDate(userInput);
        String location = EventsInputsValidator.getLocation(userInput);
        int capacity = EventsInputsValidator.getCapacity(userInput);
        String description = EventsInputsValidator.getDescription(userInput);
        int attendeeWorkloadInMinutes = EventsInputsValidator.getWorkload(userInput, "ouvinte");
        int presenterWorkloadInMinutes = EventsInputsValidator.getWorkload(userInput, "participante");

        return new AcademicFair(title, date, location, capacity, description, attendeeWorkloadInMinutes, presenterWorkloadInMinutes);
    }

    private static Event buildCourse(List<Attendee> attendees) throws Exception {
        String title = EventsInputsValidator.getTitle(userInput);
        LocalDate date = EventsInputsValidator.getDate(userInput);
        String location = EventsInputsValidator.getLocation(userInput);
        int capacity = EventsInputsValidator.getCapacity(userInput);
        int lengthInMinutes = EventsInputsValidator.getLengthInMinutes(userInput);
        String description = EventsInputsValidator.getDescription(userInput);
        Attendee speaker = EventsInputsValidator.getPresenter(userInput, attendees);

        if (speaker instanceof Professor == false)
            throw new Exception("Curso deve ser ministrado por um professor");

        return new Course(title, date, location, capacity, description, lengthInMinutes, (Professor)speaker);
    }

    private static Event buildTalk(List<Attendee> attendees) throws Exception {
        String title = EventsInputsValidator.getTitle(userInput);
        LocalDate date = EventsInputsValidator.getDate(userInput);
        String location = EventsInputsValidator.getLocation(userInput);
        int capacity = EventsInputsValidator.getCapacity(userInput);
        int lengthInMinutes = EventsInputsValidator.getLengthInMinutes(userInput);
        String description = EventsInputsValidator.getDescription(userInput);
        Attendee speaker = EventsInputsValidator.getPresenter(userInput, attendees);

        return new Talk(title, date, location, capacity, description, lengthInMinutes, speaker);
    }

    private static Event buildWorkshop(List<Attendee> attendees) throws Exception {
        String title = EventsInputsValidator.getTitle(userInput);
        LocalDate date = EventsInputsValidator.getDate(userInput);
        String location = EventsInputsValidator.getLocation(userInput);
        int capacity = EventsInputsValidator.getCapacity(userInput);
        int lengthInMinutes = EventsInputsValidator.getLengthInMinutes(userInput);
        String description = EventsInputsValidator.getDescription(userInput);
        Attendee speaker = EventsInputsValidator.getPresenter(userInput, attendees);

        return new Workshop(title, date, location, capacity, description, lengthInMinutes, speaker);
    }
}
