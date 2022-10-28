package chess.exception;

public class PathBlockedException extends Exception {

    public PathBlockedException(String errorMessage) {
        super(errorMessage);
    }

    public PathBlockedException() {
        super("a piece blocks the way");
    }
}
