package chess.exception;

public class MovementException extends Exception {
    public MovementException(String errorMessage) {
        super(errorMessage);
    }
}