package reiseplugin;

/**
 *
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class WTFException extends RuntimeException {
    /**
     * Creates a new WTFException.
     */
    public WTFException() {
        super("This should never have happened.");
    }

    /**
     * Creates a new WTFException with a message.
     * @param message The exception message.
     */
    public WTFException(String message) {
        super(message);
    }

    /**
     * Creates a new WTFException with a message and a cause.
     * @param message The exception message.
     * @param cause The cause.
     */
    public WTFException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new WTFException with a cause.
     * @param cause The cause.
     */
    public WTFException(Throwable cause) {
        super(cause);
    }
}
