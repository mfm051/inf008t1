package validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import attendees.Attendee;

public class EventsInputsValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getTitle(Scanner userInput) throws Exception {
        System.out.print("Informe o título do evento: ");
        String title = userInput.nextLine();

        if (title.isEmpty()) {
            throw new Exception("Título não pode ser vazio");
        } else {
            return title;
        }
    }

    public static String getLocation(Scanner userInput) throws Exception {
        System.out.print("Informe a localização do evento: ");
        String location = userInput.nextLine();

        if (location.isEmpty()) {
            throw new Exception("Localização não pode ser vazia");
        } else {
            return location;
        }
    }

    public static LocalDate getDate(Scanner userInput) throws Exception {
        System.out.print("Informe a data do evento (dd/mm/aaaa): ");
        String dateStr = userInput.nextLine();

        try {
            return LocalDate.parse(dateStr, DATE_FORMAT);
        } catch (Exception ex) {
            throw new Exception("Data inválida. Utilize o formato dd/mm/aaaa");
        }
    }

    public static int getCapacity(Scanner userInput) throws Exception {
        System.out.print("Informe a capacidade do evento: ");
        String capacityStr = userInput.nextLine();
        int capacity = -1;

        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (Exception ex) {
            throw new Exception("Capacidade inválida");
        }

        if (capacity <= 0)
            throw new Exception("A capacidade deve ser maior que zero");
        else
            return capacity;
    }

    public static String getDescription(Scanner userInput) throws Exception {
        System.out.print("Informe uma descrição para o evento: ");
        String description = userInput.nextLine();

        if (description.isEmpty()) {
            throw new Exception("Descrição não pode ser vazia");
        } else {
            return description;
        }
    }

    public static int getLengthInMinutes(Scanner userInput) throws Exception {
        System.out.print("Informe a duração do evento em minutos: ");
        String lengthStr = userInput.nextLine();
        int length = -1;

        try {
            length = Integer.parseInt(lengthStr);
        } catch (Exception ex) {
            throw new Exception("Número inválido. Informe um número inteiro positivo");
        }

        if (length <= 0)
            throw new Exception("A capacidade deve ser maior que zero");
        else
            return length;
    }

    public static Attendee getPresenter(Scanner userInput, List<Attendee> attendees) throws Exception {
        if (attendees.isEmpty()) {
            throw new Exception("Não existem participantes registrados");
        }

        System.out.println("Participantes cadastrados:");
        int i = 1;
        for (Attendee attendee : attendees) {
            System.out.println(i++ + ": ");
            System.out.println(attendee.getFullInfo());
        }

        System.out.print("Escolha um participante para ser o apresentador do evento: ");
        try {
            int choice = Integer.parseInt(userInput.nextLine());
            return attendees.get(choice - 1);
        } catch (Exception e) {
            throw new Exception("Opção inválida");
        }
    }

    public static int getWorkload(Scanner userInput, String attendeeType) throws Exception {
        System.out.print("Informe a carga horária para " + attendeeType + " do evento em minutos: ");
        String workloadStr = userInput.nextLine();
        int workload = -1;

        try {
            workload = Integer.parseInt(workloadStr);
        } catch (Exception ex) {
            throw new Exception("Número inválido. Informe um número inteiro positivo");
        }

        if (workload <= 0)
            throw new Exception("A carga horária deve ser maior que zero");
        else
            return workload;
    }
}
