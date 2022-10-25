package chess.movement;

import chess.exception.MovementException;
import chess.position.Board;
import chess.position.Move;

import java.util.HashSet;
import java.util.Set;

public class LimitMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    private final int limit;

    public LimitMovementEvaluator(int limit) {
        this.limit = limit;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws MovementException {

        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        if (Math.abs(fromX - toX) <= limit && Math.abs(fromY - toY) <= limit ) {
            moves.add(move);
            return moves;
        }
        else throw new MovementException();
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException {
        HashSet<Move> moves = new HashSet<>();
        moves.add(move);
        return isValidMove(board, move, moves).size() != 0;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
