package chess.movement;

import chess.exception.CannotTakePieceException;
import chess.exception.MovementException;
import chess.exception.OutOfBoundsException;
import chess.position.Board;
import chess.position.Move;
import chess.position.Position;
import java.util.Set;

public class TakeMovementEvaluator
    extends AbstractEvaluator
     {

    final boolean canTake;

    public TakeMovementEvaluator(boolean canTake) {
        this.canTake = canTake;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves)
        throws OutOfBoundsException, CannotTakePieceException, MovementException {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (
            position1 == null || position2 == null
        ) throw new OutOfBoundsException();
        if (!canTake && position2.getPiece() == null) {
            moves.add(move);
            return moves;
        }
        if (
            position1.getPiece() != null &&
            position2.getPiece() != null &&
            position2.getPiece().isTakeable(position1.getPiece())
        ) {
            if (!canTake) throw new MovementException();
            moves.add(move);
            return moves;
        } else throw new CannotTakePieceException();
    }

    @Override
    public boolean isThreatening(Board board, Move move)
        throws OutOfBoundsException, CannotTakePieceException, MovementException {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (
            position1 == null || position2 == null
        ) throw new OutOfBoundsException();
        if (!canTake && position2.getPiece() == null) {
            return true;
        }
        if (
            position1.getPiece() != null &&
            position2.getPiece() != null &&
            position2.getPiece().getPlayerColor() !=
            position1.getPiece().getPlayerColor()
        ) {
            if (!canTake) throw new MovementException();
            return true;
        } else throw new CannotTakePieceException();
    }
}
