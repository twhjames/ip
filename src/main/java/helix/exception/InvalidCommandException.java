package helix.exception;

/**
 * Exception thrown when an invalid helix.command is entered.
 */
public class InvalidCommandException extends HelixException {

    /**
     * Constructs an {@code InvalidCommandException} with a default message
     * prompting the user to check the list of valid commands.
     */
    public InvalidCommandException() {
        super("Invalid helix.command. Type 'help' for a list of commands.");
    }
}
