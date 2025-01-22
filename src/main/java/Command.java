import java.util.Locale;

public class Command {
    private final String command;

    public Command(String command) {
        this.command = command.trim().toLowerCase(Locale.ROOT);
    }

    public boolean isExit() {
        return this.command.equals("bye");
    }

    public void executeCommand() {
        System.out.println("\uD83E\uDD16 [Helix] : " + this.command + "\n");
    }
}
