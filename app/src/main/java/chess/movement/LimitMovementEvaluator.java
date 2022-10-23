package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;

public class LimitMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    private final int limit;

    public LimitMovementEvaluator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean isValidMove(Board board, Move move) throws MovementException {

        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        if (Math.abs(fromX - toX) <= limit && Math.abs(fromY - toY) <= limit ) return true;
        else throw new MovementException("The piece can not move like that");
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException {
        return isValidMove(board, move);
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
