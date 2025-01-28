package exception;

import enums.OutputSymbol;

/**
 * Base class for all Helix-related exceptions.
 */
public abstract class HelixException extends Exception {

    /**
     * Constructs a HelixException with the specified error message.
     *
     * @param message the detailed error message
     */
    public HelixException(String message) {
        super(message);
    }

    /**
     * Returns a user-friendly string representation of the exception.
     *
     * @return a formatted string with the exception message
     */
    public String getFormattedMessage() {
        return getMessage();
    }
}