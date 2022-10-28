package chess.exception;

public class MovementException extends Exception {

    public MovementException(String errorMessage) {
        super(errorMessage);
    }

    public MovementException() {
        super("The piece can not move like that");
    }
}
