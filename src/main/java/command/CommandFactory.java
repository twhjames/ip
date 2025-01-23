package command;

import enums.CommandType;
import enums.TaskType;

import exception.HelixException;
import exception.InvalidCommandException;
import exception.MissingArgumentException;
import exception.TooManyArgumentsException;
import exception.InvalidNumberFormatException;

import task.Deadline;
import task.Event;
import task.Todo;

import java.util.Locale;

/**
 * Factory class for creating Command instances based on user input.
 */
public class CommandFactory {

    /**
     * Creates a {@link Command} based on the given input.
     *
     * @param input the user input specifying the type of command
     * @return a {@link Command} instance corresponding to the input
     * @throws InvalidCommandException if the input command type is invalid
     * @throws HelixException if the command is invalid or contains improper arguments
     */
    public static Command createCommand(String input) throws HelixException {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toUpperCase(Locale.ROOT);
        String args = parts.length > 1 ? parts[1].trim() : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        return switch (commandType) {
            case TODO -> createTodoCommand(args);
            case DEADLINE -> createDeadlineCommand(args);
            case EVENT -> createEventCommand(args);
            case LIST -> createListCommand(args);
            case MARK -> createMarkCommand(args);
            case UNMARK -> createUnmarkCommand(args);
            case DELETE -> createDeleteCommand(args);
            case BYE -> createExitCommand(args);
        };
    }

    /**
     * Creates a {@link Todo} command after validating arguments.
     *
     * @param args the arguments for the {@code todo} command (task description)
     * @return a {@link Command} instance for adding a {@link Todo} task
     * @throws MissingArgumentException if the description is missing
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createTodoCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(
                    TaskType.TODO.name().toLowerCase(Locale.ROOT),
                    "todo <description>"
            );
        }
        return new AddCommand(CommandType.TODO, new Todo(args));
    }

    /**
     * Creates a {@link Deadline} command after validating arguments.
     *
     * @param args the arguments for the {@code deadline} command, which must include
     *             a description and a due date separated by {@code /by}
     * @return a {@link Command} instance for adding a {@link Deadline} task
     * @throws MissingArgumentException if the arguments are missing or improperly formatted
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createDeadlineCommand(String args) throws HelixException {
        if (!args.contains(" /by ")) {
            throw new MissingArgumentException(
                    TaskType.DEADLINE.name().toLowerCase(Locale.ROOT),
                    "deadline <description> /by <due date>"
            );
        }
        String[] details = args.split(" /by ", 2);
        if (details.length != 2 || details[1].split(" ").length > 1) {
            throw new TooManyArgumentsException(
                    TaskType.DEADLINE.name().toLowerCase(Locale.ROOT),
                    "deadline <description> /by <due date>"
            );
        }
        return new AddCommand(CommandType.DEADLINE, new Deadline(details[0].trim(), details[1].trim()));
    }

    /**
     * Creates an {@link Event} command after validating arguments.
     *
     * @param args the arguments for the {@code event} command, which must include
     *             a description, a start time (specified using {@code /from}),
     *             and an end time (specified using {@code /to})
     * @return a {@link Command} instance for adding an {@link Event} task
     * @throws MissingArgumentException if the arguments are missing or improperly formatted
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createEventCommand(String args) throws HelixException {
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new MissingArgumentException(
                    TaskType.EVENT.name().toLowerCase(Locale.ROOT),
                    "event <description> /from <start time> /to <end time>"
            );
        }
        String[] details = args.split(" /from ", 2);
        String[] times = details[1].split(" /to ", 2);
        if (details.length != 2 || times.length != 2 || times[1].split(" ").length > 1) {
            throw new TooManyArgumentsException(
                    TaskType.EVENT.name().toLowerCase(Locale.ROOT),
                    "event <description> /from <start time> /to <end time>"
            );
        }
        return new AddCommand(CommandType.EVENT, new Event(details[0].trim(), times[0].trim(), times[1].trim()));
    }

    /**
     * Creates a {@link ListCommand}.
     * The list command is used to display all tasks currently in the task list.
     *
     * @param args the arguments for the {@code list} command (should be empty)
     * @return a {@link Command} instance for listing all tasks
     * @throws TooManyArgumentsException if the command contains unnecessary arguments
     */
    private static Command createListCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException(
                    CommandType.LIST.name().toLowerCase(Locale.ROOT),
                    "list"
            );
        }
        return new ListCommand();
    }

    /**
     * Creates an {@link ExitCommand}.
     * The exit command is used to terminate the chatbot session.
     *
     * @param args the arguments for the {@code bye} command (should be empty)
     * @return a {@link Command} instance for exiting the application
     * @throws TooManyArgumentsException if the command contains unnecessary arguments
     */
    private static Command createExitCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException(
                    CommandType.BYE.name().toLowerCase(Locale.ROOT),
                    "bye"
            );
        }
        return new ExitCommand();
    }

    /**
     * Creates a {@link MarkCommand} after validating arguments.
     *
     * @param args the arguments for the {@code mark} command (task number)
     * @return a {@link Command} instance for marking a task as done
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createMarkCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(
                    CommandType.MARK.name().toLowerCase(Locale.ROOT),
                    "mark <task number>"
            );
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException(
                    CommandType.MARK.name().toLowerCase(Locale.ROOT),
                    "mark <task number>"
            );
        }
        if (!splitArgs[0].matches("\\d+")) {
            throw new InvalidNumberFormatException(
                    "The task number must be a positive integer. Example: mark <task number>"
            );
        }
        return new MarkCommand(Integer.parseInt(args) - 1);
    }

    /**
     * Creates an {@link UnmarkCommand} after validating arguments.
     *
     * @param args the arguments for the {@code unmark} command (task number)
     * @return a {@link Command} instance for marking a task as not done
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createUnmarkCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(
                    CommandType.UNMARK.name().toLowerCase(Locale.ROOT),
                    "unmark <task number>"
            );
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException(
                    CommandType.UNMARK.name().toLowerCase(Locale.ROOT),
                    "unmark <task number>"
            );
        }
        if (!splitArgs[0].matches("\\d+")) {
            throw new InvalidNumberFormatException(
                    "The task number must be a positive integer. Example: unmark <task number>"
            );
        }
        return new UnmarkCommand(Integer.parseInt(args) - 1);
    }

    /**
     * Creates a {@link DeleteCommand} after validating arguments.
     *
     * @param args the arguments for the {@code delete} command (task number)
     * @return a {@link Command} instance for deleting a task
     * @throws MissingArgumentException if the task number is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws TooManyArgumentsException if the command contains too many arguments
     */
    private static Command createDeleteCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(
                    CommandType.DELETE.name().toLowerCase(Locale.ROOT),
                    "delete <task number>"
            );
        }
        String[] splitArgs = args.split(" ");
        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException(
                    CommandType.DELETE.name().toLowerCase(Locale.ROOT),
                    "delete <task number>"
            );
        }
        if (!args.matches("\\d+")) {
            throw new InvalidNumberFormatException(
                    "The task number must be a positive integer. Example: delete <task number>"
            );
        }
        return new DeleteCommand(Integer.parseInt(args) - 1);
    }
}