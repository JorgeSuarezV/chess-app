package chess.exception;

public class CannotTakePieceException extends Exception {

    public CannotTakePieceException(String errorMessage) {
        super(errorMessage);
    }

    public CannotTakePieceException() {
        super("can not take this piece");
    }
}
