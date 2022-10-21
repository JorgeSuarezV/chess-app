package chess.piece;

import chess.movement.MovementEvaluator;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Piece implements Comparable<Piece> {

    private final Set<MovementEvaluator> movementEvaluators;
    private final boolean takeable;
    private final String name;
    private final Color color;
    private final PieceType type;




    public Piece(Set<MovementEvaluator> summativeMovementEvaluators, boolean takeable, Color color, String name, PieceType type) {
        this.movementEvaluators = summativeMovementEvaluators;
        this.takeable = takeable;
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public boolean isTakeable(Piece piece) {
        return takeable && piece.getColor() != getColor();
    }

    @Override
    public int compareTo(@NotNull Piece o) {
        if (o.getName().equals(name)) return 0;
        if (o.getType() == type) return 1;
        return -1;
    }

    public PieceType getType() {
        return type;
    }

    public Set<MovementEvaluator> getSummativeMovementEvaluators() {
        return movementEvaluators;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
