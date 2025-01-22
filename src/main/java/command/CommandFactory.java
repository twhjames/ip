package command;

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
        return switch (input) {
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            default -> new AddCommand(input);
        };
    }
}

