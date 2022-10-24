package chess.position;

import chess.exception.*;
import chess.piece.Piece;
import chess.piece.PieceType;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.dissis.chess.gui.PlayerColor;

import static adapter.Adapter.getChessPieceList;
import static chess.piece.PieceFactory.createQueen;

public class PromotionValidator implements Validator{

    PieceType from;

    public PromotionValidator(PieceType from) {
        this.from = from;
    }

    @Override
    public MoveResult checkMove(Board board, Move move, PlayerColor currentPlayerColor) throws OutOfBoundsException {
        Position toPosition = board.getPosition(move.getTo());
        if (toPosition == null) throw new OutOfBoundsException();
        if ((currentPlayerColor == PlayerColor.WHITE && toPosition.getY() == 7) ||
        currentPlayerColor == PlayerColor.BLACK && toPosition.getY() == 0){
            if (toPosition.getPiece().getType() == from){
                convertToQueen(toPosition.getPiece(), currentPlayerColor);
                currentPlayerColor = currentPlayerColor == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
                return new NewGameState(getChessPieceList(board.getActualPositions()), currentPlayerColor);
            }
        }
        return null;
    }

    private void convertToQueen(Piece piece, PlayerColor color){
        Piece queen = createQueen(color);
        piece.setType(queen.getType());
        piece.setColor(queen.getPlayerColor());
        piece.setIllegalMovementEvaluators(queen.getIllegalMovementEvaluators());
        piece.setMovementEvaluators(queen.getMovementEvaluators());
    }
}
