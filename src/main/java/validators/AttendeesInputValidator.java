package validators;

import java.util.Scanner;

public class AttendeesInputValidator {
    public static String getName(Scanner userInput) throws Exception {
        System.out.print("Informe o nome do participante: ");
        String title = userInput.nextLine();

        if (title.isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        } else {
            return title;
        }
    }

    public static int getId(Scanner userInput, String idType) throws Exception {
        System.out.print("Informe um número de identificação para o participante (" + idType + "): ");
        String idStr = userInput.nextLine();
        int id = -1;

        try {
            id = Integer.parseInt(idStr);
        } catch (Exception ex) {
            throw new Exception("Capacidade inválida");
        }

        if (id <= 0)
            throw new Exception("A capacidade deve ser maior que zero");
        else
            return id;
    }
}
