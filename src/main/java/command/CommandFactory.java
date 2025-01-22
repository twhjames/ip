package command;

import task.Deadline;
import task.Event;
import task.Todo;

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
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase(Locale.ROOT);
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "todo" -> new AddCommand(new Todo(args));
            case "deadline" -> {
                String[] details = args.split(" /by ", 2);
                yield new AddCommand(new Deadline(details[0], details[1]));
            }
            case "event" -> {
                String[] details = args.split(" /from ", 2);
                String[] times = details[1].split(" /to ", 2);
                yield new AddCommand(new Event(details[0], times[0], times[1]));
            }
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(args) - 1);
            case "unmark" -> new UnmarkCommand(Integer.parseInt(args) - 1);
            default -> throw new IllegalArgumentException("Unknown command: " + command);
        };
    }
}

