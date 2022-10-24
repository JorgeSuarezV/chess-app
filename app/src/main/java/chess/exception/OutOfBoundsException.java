package chess.exception;

public class OutOfBoundsException extends Exception {
    public OutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
    public OutOfBoundsException() {
        super("From or To position OOB");
    }
}
