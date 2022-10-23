package chess.position;

import chess.exception.*;
import chess.movement.MovementEvaluator;
import chess.piece.Piece;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.Set;

import static chess.Adapter.getChessPieceList;

public class ClassicMoveValidator implements Validator{

    Set<MovementEvaluator> gameEvaluators;

    public ClassicMoveValidator(Set<MovementEvaluator> gameEvaluators) {
        this.gameEvaluators = gameEvaluators;
    }

    @Override
    public MoveResult checkMove(Board board, Move move, PlayerColor currentPlayerColor) throws OutOfBoundsException, NotMovingAPieceException, MovementException, PathBlockedException, CannotTakePieceException, SelfCheckException {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());

        if (fromPosition == null || toPosition == null) throw new OutOfBoundsException("From or To position OOB");
        if (fromPosition.compareTo(toPosition) == 0) throw new NotMovingAPieceException("Not moving a piece");

        Piece piece = fromPosition.getPiece();
        if (piece == null) throw new NotMovingAPieceException("Not moving a piece");
        if (piece.getPlayerColor() != currentPlayerColor) throw new MovementException("This piece is not yours");

        boolean atLeastOneTrue = false;
        int errors = piece.getMovementEvaluators().size();
        int count = 0;
        for (MovementEvaluator summativeMovementEvaluator : piece.getMovementEvaluators()) {
            try {
                if (summativeMovementEvaluator.isValidMove(board, move)) {
                    atLeastOneTrue = true;
                    break;
                }
            }catch (OutOfBoundsException|NotMovingAPieceException|MovementException|PathBlockedException ignored){
            }
        }
        if (!atLeastOneTrue) throw new MovementException("The piece can not move like that");

        for (MovementEvaluator illegalMovementEvaluator : piece.getIllegalMovementEvaluators()) {
            illegalMovementEvaluator.isValidMove(board, move);
        }

        board.movePiece(move);
        return new NewGameState(getChessPieceList(board.getActualPositions()), currentPlayerColor);
    }
}
