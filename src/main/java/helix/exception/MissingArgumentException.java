package helix.exception;

/**
 * Exception thrown when a helix.command is missing required arguments.
 */
public class MissingArgumentException extends HelixException {

    /**
     * Constructs a {@code MissingArgumentException} with the specific helix.command
     * name and an example format to guide the user.
     *
     * @param command       the name of the helix.command missing required arguments (e.g., "todo", "deadline")
     * @param exampleFormat an example format showing the correct syntax for the helix.command
     */
    public MissingArgumentException(String command, String exampleFormat) {
        super("The helix.command '" + command + "' requires additional arguments. "
                + "Example: " + exampleFormat);
    }
}
