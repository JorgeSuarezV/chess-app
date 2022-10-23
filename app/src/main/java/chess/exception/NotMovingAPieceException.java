package chess.exception;

public class NotMovingAPieceException extends Exception {
    public NotMovingAPieceException(String errorMessage) {
        super(errorMessage);
    }
}
