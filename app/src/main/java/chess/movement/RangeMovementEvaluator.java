package chess.movement;

import chess.exception.*;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.position.Board;
import chess.position.Move;
import chess.position.Position;
import java.util.Set;

public class RangeMovementEvaluator
    extends AbstractEvaluator
     {

    private int shots;
    private final int range;
    private final Set<PieceType> targets;

    public RangeMovementEvaluator(
        int shots,
        int range,
        Set<PieceType> targets
    ) {
        this.shots = shots;
        this.range = range;
        this.targets = targets;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves)
        throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position toPosition = board.getPosition(move.getTo());
        Position fromPosition = board.getPosition(move.getFrom());
        if (
            toPosition == null || fromPosition == null
        ) throw new OutOfBoundsException();
        if (!isThreatening(board, move)) throw new MovementException();
        if (toPosition.getPiece().isTakeable(fromPosition.getPiece())) {
            shots--;
            moves.add(move);
            moves.add(new Move(move.getTo(), move.getFrom()));
            return checkValidMoveWithEvaluators(board, move, moves);
        }
        throw new MovementException();
    }

    @Override
    public boolean isThreatening(Board board, Move move)
        throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        if (
            move.getFrom().compareTo(move.getTo()) == 0
        ) throw new MovementException();
        Position position = board.getPosition(move.getTo());
        if (position == null) throw new OutOfBoundsException();
        Piece piece = position.getPiece();
        if (piece == null) throw new MovementException();
        if (!targets.contains(piece.getType())) throw new MovementException();
        if (shots == 0) throw new MovementException();
        if (
            Math.abs(move.getFrom().getX() - move.getTo().getX()) <= range &&
            Math.abs(move.getFrom().getY() - move.getTo().getY()) <= range
        ) {
            return true;
        }
        return false;
    }
}
