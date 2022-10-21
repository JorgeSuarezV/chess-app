package chess.movement;

import chess.position.Board;
import chess.position.Move;

public class LimitMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    private final int limit;

    public LimitMovementEvaluator(int limit) {
        this.limit = limit;
    }

    @Override
    public String isValidMove(Board board, Move move) {
        if (move.getFrom().compareTo(move.getTo()) == 0) return "invalid move";

        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        if (Math.abs(fromX - toX) <= limit && Math.abs(fromY - toY) <= limit ) return "ok";
        else return "invalid move";
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
