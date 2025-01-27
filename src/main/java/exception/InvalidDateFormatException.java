package exception;

/**
 * Exception thrown when a date format is invalid.
 */
public class InvalidDateFormatException extends HelixException {

    /**
     * Constructs an InvalidDateFormatException with a detailed error message.
     *
     * @param message the error message
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}