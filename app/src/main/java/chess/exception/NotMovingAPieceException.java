package chess.exception;

public class NotMovingAPieceException extends Exception {

    public NotMovingAPieceException(String errorMessage) {
        super(errorMessage);
    }

    public NotMovingAPieceException() {
        super("Not moving a piece");
    }
}
