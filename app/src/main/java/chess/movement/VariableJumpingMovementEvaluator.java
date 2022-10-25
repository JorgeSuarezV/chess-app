package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.Set;

public class VariableJumpingMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    int x;
    int y;

    public VariableJumpingMovementEvaluator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        if (VariableJumpingLogic(board, move)) return reachedPosition(board, move, moves);
        throw new MovementException();
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        if (VariableJumpingLogic(board, move)) return checkTargetMoveWithEvaluators(board, move);
        throw new MovementException();
    }

    private boolean VariableJumpingLogic(Board board, Move move) throws OutOfBoundsException {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());
        if (fromPosition == null || toPosition == null || fromPosition.getPiece() == null) throw new OutOfBoundsException();

        int xFrom = move.getFrom().getX();
        int yFrom = move.getFrom().getY();

        Position endPosition = board.getPosition(new Coordinate(xFrom + x, yFrom + y));
        return endPosition != null && toPosition.compareTo(endPosition) == 0;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
