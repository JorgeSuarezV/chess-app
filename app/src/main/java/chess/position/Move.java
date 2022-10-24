package chess.position;

public class Move {
    private final Coordinate from;
    private final Coordinate to;

    public Move(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        Move otherMove = (Move) obj;
        return this.getFrom().equals(otherMove.getFrom()) &&
                this.getTo().equals(otherMove.getTo());
    }

    @Override
    public int hashCode() {
        return (7897894);
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }
}
