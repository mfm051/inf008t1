import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputsValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getNameFromInput(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        else {
            return name;
        }
    }

    public static LocalDate getDateFromInput(String dateStr) throws Exception {
        try {
            return LocalDate.parse(dateStr, DATE_FORMAT);
        }
        catch (Exception ex) {
            throw new Exception("Data inválida");
        }
    }

    public static int getIntFromInput(String intStr) throws Exception {
        try {
            return Integer.parseInt(intStr);
        }
        catch (Exception ex) {
            throw new Exception("Capacidade fornecida inválida");
        }
    }
}
