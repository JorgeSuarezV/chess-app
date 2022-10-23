package chess.exception;

public class SelfCheckException extends Exception {
    public SelfCheckException(String errorMessage) {
        super(errorMessage);
    }
}
