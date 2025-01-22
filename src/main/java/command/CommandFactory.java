package command;

import exception.*;
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
     * @throws HelixException if the command is invalid or missing arguments
     */
    public static Command createCommand(String input) throws HelixException {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase(Locale.ROOT);
        String args = parts.length > 1 ? parts[1].trim() : "";

        return switch (command) {
            case "todo" -> createTodoCommand(args);
            case "deadline" -> createDeadlineCommand(args);
            case "event" -> createEventCommand(args);
            case "list" -> createListCommand(args);
            case "bye" -> createExitCommand(args);
            case "mark" -> createMarkCommand(args);
            case "unmark" -> createUnmarkCommand(args);
            case "delete" -> createDeleteCommand(args);
            default -> throw new InvalidCommandException();
        };
    }

    /**
     * Creates a {@link Todo} command after validating arguments.
     *
     * @param args the arguments for the todo command (task description)
     * @return a {@link Command} instance for adding a todo task
     * @throws MissingArgumentException if the description is missing
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createTodoCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("todo", "todo <description>");
        }
        if (args.split(" ").length > 1) {
            throw new TooManyArgumentsException("todo", "todo <description>");
        }
        return new AddCommand(new Todo(args));
    }

    /**
     * Creates a {@link Deadline} command after validating arguments.
     *
     * @param args the arguments for the deadline command, which must include
     *             a description and a due date separated by " /by "
     * @return a {@link Command} instance for adding a deadline task
     * @throws MissingArgumentException if the arguments are missing or
     *                                  improperly formatted
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createDeadlineCommand(String args) throws HelixException {
        if (!args.contains(" /by ")) {
            throw new MissingArgumentException("deadline", "deadline <description> /by <due date>");
        }
        String[] details = args.split(" /by ", 2);
        if (details.length != 2 || details[1].split(" ").length > 1) {
            throw new TooManyArgumentsException("deadline", "deadline <description> /by <due date>");
        }
        return new AddCommand(new Deadline(details[0].trim(), details[1].trim()));
    }

    /**
     * Creates an {@link Event} command after validating arguments.
     *
     * @param args the arguments for the event command, which must include
     *             a description, a start time (specified using "/from"),
     *             and an end time (specified using "/to")
     * @return a {@link Command} instance for adding an event task
     * @throws MissingArgumentException if the arguments are missing or
     *                                  improperly formatted
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createEventCommand(String args) throws HelixException {
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new MissingArgumentException("event", "event <description> /from <start time> /to <end time>");
        }
        String[] details = args.split(" /from ", 2);
        String[] times = details[1].split(" /to ", 2);
        if (details.length != 2 || times.length != 2 || times[1].split(" ").length > 1) {
            throw new TooManyArgumentsException("event", "event <description> /from <start time> /to <end time>");
        }
        return new AddCommand(new Event(details[0].trim(), times[0].trim(), times[1].trim()));
    }

    /**
     * Creates a {@link ListCommand}.
     * The list command is used to display all tasks currently in the task list.
     *
     * @param args the arguments for the list command (should be empty)
     * @return a {@link Command} instance for listing all tasks
     * @throws TooManyArgumentsException if the command contains unnecessary arguments
     */
    private static Command createListCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException("list", "list");
        }
        return new ListCommand();
    }

    /**
     * Creates an {@link ExitCommand}.
     * The exit command is used to terminate the chatbot session.
     *
     * @param args the arguments for the exit command (should be empty)
     * @return a {@link Command} instance for exiting the application
     * @throws TooManyArgumentsException if the command contains unnecessary arguments
     */
    private static Command createExitCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException("bye", "bye");
        }
        return new ExitCommand();
    }

    /**
     * Creates a {@link MarkCommand} after validating arguments.
     * The mark command is used to mark a specific task as done. The task
     * number must be a positive integer corresponding to the index of the
     * task in the task list.
     *
     * @param args the arguments for the mark command (task number)
     * @return a {@link Command} instance for marking a task as done
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createMarkCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("mark", "mark <task number>");
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException("mark", "mark <task number>");
        }
        if (!splitArgs[0].matches("\\d+")) {
            throw new InvalidNumberFormatException("The task number must be a positive integer. Example: mark <task number>");
        }
        return new MarkCommand(Integer.parseInt(args) - 1);
    }

    /**
     * Creates an {@link UnmarkCommand} after validating arguments.
     * The unmark command is used to mark a specific task as not done. The task
     * number must be a positive integer corresponding to the index of the
     * task in the task list.
     *
     * @param args the arguments for the unmark command (task number)
     * @return a {@link Command} instance for marking a task as not done
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createUnmarkCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("unmark", "unmark <task number>");
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException("unmark", "unmark <task number>");
        }
        if (!splitArgs[0].matches("\\d+")) {
            throw new InvalidNumberFormatException("The task number must be a positive integer. Example: unmark <task number>");
        }
        return new UnmarkCommand(Integer.parseInt(args) - 1);
    }

    /**
     * Creates a {@link DeleteCommand} after validating arguments.
     *
     * @param args the arguments for the delete command (task number)
     * @return a {@link Command} instance for deleting a task
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     */
    private static Command createDeleteCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("delete", "delete <task number>");
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException("delete", "delete <task number>");
        }
        if (!args.matches("\\d+")) {
            throw new InvalidNumberFormatException("The task number must be a positive integer. Example: delete <task number>");
        }
        return new DeleteCommand(Integer.parseInt(args) - 1);
    }
}