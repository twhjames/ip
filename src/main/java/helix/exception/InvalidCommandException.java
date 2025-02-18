package helix.exception;

/**
 * Signals that an invalid command has been entered.
 */
public class InvalidCommandException extends HelixException {

    /**
     * Constructs an {@code InvalidCommandException} with a default message
     * prompting the user to check the list of valid commands.
     */
    public InvalidCommandException() {
        super("Invalid command. Type 'help' for a list of commands.");
    }

    /**
     * Constructs an InvalidCommandException with a specific error message.
     *
     * @param message The detailed error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
