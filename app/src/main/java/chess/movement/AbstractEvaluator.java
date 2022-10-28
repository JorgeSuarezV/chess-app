package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;
import chess.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEvaluator implements MovementEvaluator {

    private final Set<MovementEvaluator> movementEvaluators = new HashSet<>();

    public Set<Move> reachedPosition(Board board, Move move, Set<Move> moves)
        throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position toPosition = board.getPosition(move.getTo());
        Position fromPosition = board.getPosition(move.getFrom());
        if (
            toPosition == null || fromPosition == null
        ) throw new OutOfBoundsException();
        if (toPosition.getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move, moves);
        } else if (
            toPosition.getPiece() != null &&
            fromPosition.getPiece() != null &&
            toPosition.getPiece().isTakeable(fromPosition.getPiece())
        ) {
            return checkValidMoveWithEvaluators(board, move, moves);
        } else throw new CannotTakePieceException();
    }

    public Set<Move> checkValidMoveWithEvaluators(
        Board board,
        Move move,
        Set<Move> moves
    )
        throws NotMovingAPieceException, MovementException, PathBlockedException, OutOfBoundsException, CannotTakePieceException, SelfCheckException {
        for (MovementEvaluator movementEvaluator : movementEvaluators) {
            boolean result =
                movementEvaluator.isValidMove(board, move, moves).size() != 0;
            if (!result) throw new MovementException();
        }
        moves.add(move);
        return moves;
    }

    public boolean checkTargetMoveWithEvaluators(Board board, Move move)
        throws NotMovingAPieceException, MovementException, PathBlockedException, OutOfBoundsException, CannotTakePieceException, SelfCheckException {
        for (MovementEvaluator movementEvaluator : movementEvaluators) {
            boolean result = movementEvaluator.isThreatening(board, move);
            if (!result) throw new MovementException();
        }
        return true;
    }

    public MovementEvaluator addMovementEvaluator(
        MovementEvaluator movementEvaluator
    ) {
        movementEvaluators.add(movementEvaluator);
        return this;
    }
}
