package chess.piece;

import chess.movement.MovementEvaluator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Piece implements Takeable,Comparable<Piece> {

    private final List<MovementEvaluator> summativeMovementEvaluators;
    private final List<MovementEvaluator> restrictiveMovementEvaluator;
    private final boolean takeable;
    private String name;
    private final Color color;




    public Piece(List<MovementEvaluator> summativeMovementEvaluators, List<MovementEvaluator> restrictiveMovementEvaluator, boolean takeable, Color color, String name) {
        this.summativeMovementEvaluators = summativeMovementEvaluators;
        this.restrictiveMovementEvaluator = restrictiveMovementEvaluator;
        this.takeable = takeable;
        this.color = color;
        this.name = name;
    }

    @Override
    public boolean isTakeable(Piece piece) {
        return takeable && piece.getColor() != getColor();

    }

    @Override
    public int compareTo(@NotNull Piece o) {
        if (o.getName().equals(this.getName())) return 0;
        if (o.getName().charAt(0) == this.getName().charAt(0)) return 1;
        return -1;
    }

    public List<MovementEvaluator> getSummativeMovementEvaluators() {
        return summativeMovementEvaluators;
    }

    public List<MovementEvaluator> getRestrictiveMovementEvaluator() {
        return restrictiveMovementEvaluator;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
