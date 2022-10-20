package piece;

public interface Takeable {

    default boolean isTakeable(Piece piece) {
        return true;
    }
}
