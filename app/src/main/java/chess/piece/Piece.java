package chess.piece;

import chess.movement.MovementEvaluator;
import edu.austral.dissis.chess.gui.PlayerColor;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Piece implements Comparable<Piece>,Cloneable {

    private Set<MovementEvaluator> movementEvaluators;
    private Set<MovementEvaluator> illegalMovementEvaluators;
    private boolean takeable;
    private String name;
    private PlayerColor color;
    private PieceType type;




    public Piece(Set<MovementEvaluator> MovementEvaluators, Set<MovementEvaluator> illegalMovementEvaluators, boolean takeable, PlayerColor color, String name, PieceType type) {
        this.movementEvaluators = MovementEvaluators;
        this.illegalMovementEvaluators = illegalMovementEvaluators;
        this.takeable = takeable;
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public boolean isTakeable() {
        return takeable;
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

    public void setType(PieceType type) {
        this.type = type;
    }

    public void setMovementEvaluators(Set<MovementEvaluator> movementEvaluators) {
        this.movementEvaluators = movementEvaluators;
    }

    public void setIllegalMovementEvaluators(Set<MovementEvaluator> illegalMovementEvaluators) {
        this.illegalMovementEvaluators = illegalMovementEvaluators;
    }

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public Set<MovementEvaluator> getIllegalMovementEvaluators() {
        return illegalMovementEvaluators;
    }
}
