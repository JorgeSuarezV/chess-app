package chess.piece;

import chess.movement.MovementEvaluator;
import edu.austral.dissis.chess.gui.PlayerColor;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Piece implements Comparable<Piece>,Cloneable {

    private final Set<MovementEvaluator> movementEvaluators;
    private final Set<MovementEvaluator> illegalMovementEvaluators;
    private final boolean takeable;
    private final String name;
    private final PlayerColor color;
    private final PieceType type;




    public Piece(Set<MovementEvaluator> MovementEvaluators, Set<MovementEvaluator> illegalMovementEvaluators, boolean takeable, PlayerColor color, String name, PieceType type) {
        this.movementEvaluators = MovementEvaluators;
        this.illegalMovementEvaluators = illegalMovementEvaluators;
        this.takeable = takeable;
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public Piece clone(){
        return new Piece(movementEvaluators, illegalMovementEvaluators, takeable, color, name, type);
    }

    public boolean isTakeable(Piece piece) {
        return takeable && piece.getPlayerColor() != getPlayerColor();
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

    public Set<MovementEvaluator> getMovementEvaluators() {
        return movementEvaluators;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }

    public Set<MovementEvaluator> getIllegalMovementEvaluators() {
        return illegalMovementEvaluators;
    }
}
