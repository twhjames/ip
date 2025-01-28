package helix.exception;

/**
 * Exception thrown when an invalid number is provided as input.
 */
public class InvalidNumberFormatException extends HelixException {

    /**
     * Constructs an {@code InvalidNumberFormatException} with a default error message.
     */
    public InvalidNumberFormatException() {
        super("Invalid input. Please enter a number.");
    }

    /**
     * Constructs an {@code InvalidNumberFormatException} with a custom error message.
     *
     * @param message the custom error message to be displayed
     */
    public InvalidNumberFormatException(String message) {
        super(message);
    }
}