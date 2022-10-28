package chess.position;

import static adapter.Adapter.getChessPieceList;

import chess.exception.*;
import chess.movement.MovementEvaluator;
import chess.piece.Piece;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.dissis.chess.gui.PlayerColor;
import java.util.HashSet;
import java.util.Set;

public class ClassicMoveValidator implements Validator {

    @Override
    public MoveResult checkMove(
        Board board,
        Move move,
        PlayerColor currentPlayerColor
    )
        throws OutOfBoundsException, NotMovingAPieceException, MovementException, PathBlockedException, CannotTakePieceException, SelfCheckException {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());

        if (
            fromPosition == null || toPosition == null
        ) throw new OutOfBoundsException();
        if (
            fromPosition.compareTo(toPosition) == 0
        ) throw new NotMovingAPieceException();

        Piece piece = fromPosition.getPiece();
        if (piece == null) throw new NotMovingAPieceException();
        if (
            piece.getPlayerColor() != currentPlayerColor
        ) throw new MovementException("This piece is not yours");

        HashSet<Move> moves = new HashSet<>();

        checkMovementEvaluators(board, move, moves, piece);

        checkIllegalMovementEvaluators(board, move, moves, piece);

        board.movePiece(moves);
        currentPlayerColor =
            currentPlayerColor == PlayerColor.WHITE
                ? PlayerColor.BLACK
                : PlayerColor.WHITE;
        return new NewGameState(
            getChessPieceList(board.getActualPositions()),
            currentPlayerColor
        );
    }

    private void checkIllegalMovementEvaluators(
        Board board,
        Move move,
        Set<Move> moves,
        Piece piece
    )
        throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        for (MovementEvaluator illegalMovementEvaluator : piece.getIllegalMovementEvaluators()) {
            illegalMovementEvaluator.isValidMove(board, move, moves);
        }
    }

    private void checkMovementEvaluators(
        Board board,
        Move move,
        Set<Move> moves,
        Piece piece
    ) throws CannotTakePieceException, SelfCheckException, MovementException {
        boolean atLeastOneTrue = false;
        for (MovementEvaluator summativeMovementEvaluator : piece.getMovementEvaluators()) {
            try {
                if (
                    summativeMovementEvaluator
                        .isValidMove(board, move, moves)
                        .size() != 0
                ) {
                    atLeastOneTrue = true;
                    break;
                }
            } catch (
                OutOfBoundsException
                | NotMovingAPieceException
                | MovementException
                | PathBlockedException ignored
            ) {}
        }
        if (!atLeastOneTrue) throw new MovementException();
    }
}
