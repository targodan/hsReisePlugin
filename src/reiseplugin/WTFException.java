package reiseplugin;

/**
 *
 * @author Luca Corbatto
 */
public class WTFException extends RuntimeException {
    public WTFException() {
        super("This should never have happened.");
    }

    public WTFException(String message) {
        super(message);
    }

    public WTFException(String message, Throwable cause) {
        super(message, cause);
    }

    public WTFException(Throwable cause) {
        super(cause);
    }
}
