package chess.exception;

public class SelfCheckException extends Exception {

    public SelfCheckException(String errorMessage) {
        super(errorMessage);
    }

    public SelfCheckException() {
        super("can not leave your king in check");
    }
}
