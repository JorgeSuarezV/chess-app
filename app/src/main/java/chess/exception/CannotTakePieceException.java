package chess.exception;

public class CannotTakePieceException extends Exception {
    public CannotTakePieceException(String errorMessage) {
        super(errorMessage);
    }
}