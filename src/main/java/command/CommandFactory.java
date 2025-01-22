package command;

import java.util.Locale;

/**
 * Factory class for creating Command instances based on user input.
 */
public class CommandFactory {
    /**
     * Creates a Command based on the given input.
     *
     * @param input the user input specifying the type of command
     * @return a Command instance corresponding to the input
     */
    public static Command createCommand(String input) {
        // split input
        String[] parts = input.trim().split(" ");
        String command = parts[0].toLowerCase(Locale.ROOT);
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(args) - 1);
            case "unmark" -> new UnmarkCommand(Integer.parseInt(args) - 1);
            default -> new AddCommand(input);
        };
    }
}

