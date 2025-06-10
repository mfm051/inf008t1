package user_cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import attendees.Attendee;
import attendees.AttendeeBuilderFunction;
import attendees.AttendeesInputValidator;
import attendees.ExternalAttendee;
import attendees.Professor;
import attendees.Student;


public class AttendeeBuilder {
    private static final Map<String, AttendeeBuilderFunction> attendeeBuilders = new HashMap<>();
    private static final Scanner userInput = new Scanner(System.in);

    static {
        attendeeBuilders.put("professor", AttendeeBuilder::buildProfessor);
        attendeeBuilders.put("aluno", AttendeeBuilder::buildStudent );
        attendeeBuilders.put("participante externo", AttendeeBuilder::buildExternalAttendee);
    }

    public static Attendee createAttendeeFromUserInput() throws Exception {
        System.out.println("Participantes disponíveis: " + attendeeBuilders.keySet());
        System.out.print("Escolha um participante: ");
        String type = userInput.nextLine().trim().toLowerCase();

        AttendeeBuilderFunction builder = attendeeBuilders.get(type);
        if (builder != null) {
            return builder.build();
        } else {
            throw new Exception("Tipo de participante inválido");
        }
    }

    private static Attendee buildProfessor() throws Exception {
        String name = AttendeesInputValidator.getName(userInput);
        int id = AttendeesInputValidator.getId(userInput, "nº de matrícula");

        return new Professor(name, id);
    }

    private static Attendee buildStudent() throws Exception {
        String name = AttendeesInputValidator.getName(userInput);
        int id = AttendeesInputValidator.getId(userInput, "nº de matrícula");

        return new Student(name, id);
    }

    private static Attendee buildExternalAttendee() throws Exception {
        String name = AttendeesInputValidator.getName(userInput);
        int id = AttendeesInputValidator.getId(userInput, "CPF (apenas números)");

        return new ExternalAttendee(name, id);
    }
}
