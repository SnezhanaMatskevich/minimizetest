package by.bsuir.group172301.matskevich.tour.exception;

/**
 *
 */
public class AuthenticationLogicalException extends Exception{
    public AuthenticationLogicalException() {
    }

    public AuthenticationLogicalException(String message) {
        super(message);
    }

    public AuthenticationLogicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationLogicalException(Throwable cause) {
        super(cause);
    }

    public AuthenticationLogicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
