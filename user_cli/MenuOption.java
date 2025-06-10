package user_cli;

public enum MenuOption {
    EXIT(0, "Sair"),
    REGISTER_EVENT(1, "Cadastrar um evento"),
    REGISTER_ATTENDEE(2, "Cadastrar um participante"),
    REGISTER_ATTENDEE_IN_EVENT(3, "Registrar um participante em um evento"),
    SHOW_CERTIFICATE(4, "Obter certificado"),
    SHOW_REPORT(5, "Obter um relatório dos eventos");

    public final int code;
    public final String description;

    MenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static void showMenu() {
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.code + ": " + option.description);
        }
    }
}