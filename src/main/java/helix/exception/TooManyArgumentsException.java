package helix.exception;

/**
 * Exception thrown when a helix.command has more arguments than expected.
 */
public class TooManyArgumentsException extends HelixException {

    /**
     * Constructs a {@code TooManyArgumentsException} with a default error message.
     */
    public TooManyArgumentsException() {
        super("Too many arguments provided. Please check the helix.command format.");
    }

    /**
     * Constructs a {@code TooManyArgumentsException} with a custom error message.
     *
     * @param command the helix.command name
     * @param exampleFormat an example of the correct helix.command format
     */
    public TooManyArgumentsException(String command, String exampleFormat) {
        super("The helix.command '" + command + "' received too many arguments. "
                + "Example: " + exampleFormat);
    }
}
