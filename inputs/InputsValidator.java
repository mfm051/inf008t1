package inputs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputsValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Scanner userInput = new Scanner(System.in);

    public static String getNameFromInput() throws Exception {
        String name = userInput.nextLine();

        if (name.isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        else {
            return name;
        }
    }

    public static LocalDate getDateFromInput() throws Exception {
        String dateStr = userInput.nextLine();

        try {
            return LocalDate.parse(dateStr, DATE_FORMAT);
        }
        catch (Exception ex) {
            throw new Exception("Data inválida");
        }
    }

    public static int getIntFromInput() throws Exception {
        String intStr = userInput.nextLine();

        try {
            return Integer.parseInt(intStr);
        }
        catch (Exception ex) {
            throw new Exception("Capacidade fornecida inválida");
        }
    }
}
