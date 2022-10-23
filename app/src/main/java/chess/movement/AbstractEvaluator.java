package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;
import chess.position.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEvaluator implements MovementEvaluator{

    private final Set<MovementEvaluator> movementEvaluators = new HashSet<>();


    public boolean reachedPosition(Board board, Move move) throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position toPosition = board.getPosition(move.getTo());
        Position fromPosition = board.getPosition(move.getFrom());
        if (toPosition == null || fromPosition == null) throw new OutOfBoundsException("From or To position OOB");
        if (toPosition.getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else if (toPosition.getPiece() != null && fromPosition.getPiece() != null && toPosition.getPiece().isTakeable(fromPosition.getPiece())) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else throw new CannotTakePieceException("can not take this piece");
    }

    public boolean checkValidMoveWithEvaluators(Board board, Move move) throws NotMovingAPieceException, MovementException, PathBlockedException, OutOfBoundsException, CannotTakePieceException, SelfCheckException {
        for (MovementEvaluator movementEvaluator : movementEvaluators) {
            boolean result = movementEvaluator.isValidMove(board, move);
            if (!result) throw new MovementException("The piece can not move like that");
        }
        return true;
    }

    public boolean checkTargetMoveWithEvaluators(Board board, Move move) throws NotMovingAPieceException, MovementException, PathBlockedException, OutOfBoundsException, CannotTakePieceException, SelfCheckException {
        for (MovementEvaluator movementEvaluator : movementEvaluators) {
            boolean result = movementEvaluator.isThreatening(board, move);
            if (!result) throw new MovementException("The piece can not move like that");
        }
        return true;
    }

    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        movementEvaluators.add(movementEvaluator);
        return this;
    }
}
