package user_cli;

import java.util.Scanner;

public class AcademicManagement {
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler(userInput);

        while (true) {
            MenuOption.showMenu();
            System.out.print("Escolha uma opção: ");
            int choice;

            try {
                choice = Integer.parseInt(userInput.nextLine());
            } catch (Exception ex) {
                System.out.println("Não foi possível identificar o número. Tente novamente");
                continue;
            }

            if (choice == MenuOption.EXIT.code)
                break;

            Runnable userAction = handler.getActionByCode(choice);

            if (userAction != null) {
                userAction.run();
            } else {
                System.out.println("Opção inválida");
            }
        }
    }
}
