package position;

import piece.Piece;
import org.jetbrains.annotations.NotNull;

public class Position implements Comparable<Position>{

    private final Coordinate coordinate;
    private Piece piece = null;

    public Position(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }

    public Position(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public int getX() {
        return coordinate.getX();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getY() {
        return coordinate.getY();
    }

    public Piece getPiece() {
        return piece; // null when no piece found
    }

    public Piece setPiece(Piece piece) {
        Piece piece1 = this.piece;
        this.piece = piece;
        return piece1;
    }

    @Override
    public int compareTo(@NotNull Position o) {
        return coordinate.compareTo(o.getCoordinate());
    }
}