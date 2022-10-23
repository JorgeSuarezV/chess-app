package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;
import chess.position.Position;

public class TakeMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    final boolean canTake;

    public TakeMovementEvaluator(boolean canTake) {
        this.canTake = canTake;
    }

    @Override
    public boolean isValidMove(Board board, Move move) throws OutOfBoundsException, CannotTakePieceException {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (position1 == null || position2 == null) throw new OutOfBoundsException("From or To position OOB");
        if (!canTake && position2.getPiece() == null) return true;
        if (position1.getPiece() != null && position2.getPiece() != null && position2.getPiece().isTakeable(position1.getPiece())) return canTake;
        else throw new CannotTakePieceException("can not take this piece");
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws OutOfBoundsException, CannotTakePieceException {
        return isValidMove(board, move);
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
