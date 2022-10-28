package chess.position;

import chess.piece.Piece;
import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.PlayerColor;
import java.util.List;

public class WinValidator implements Validator {

    private final Validator validator = new ClassicMoveValidator();

    @Override
    public MoveResult checkMove(
        Board board,
        Move move,
        PlayerColor currentPlayerColor
    ) {
        board = board.clone();
        List<Position> actualPositions = board.getActualPositions();
        PlayerColor oponentColor = currentPlayerColor == PlayerColor.WHITE
            ? PlayerColor.BLACK
            : PlayerColor.WHITE;

        for (Position actualPosition : actualPositions) {
            Piece piece = actualPosition.getPiece();
            if (
                piece == null || piece.getPlayerColor() == currentPlayerColor
            ) continue;
            for (Position toPosition : actualPositions) {
                Move move1 = new Move(
                    actualPosition.getCoordinate(),
                    toPosition.getCoordinate()
                );
                try {
                    validator.checkMove(board, move1, oponentColor);
                    return null;
                } catch (Exception ignored) {}
            }
        }
        return new GameOver(currentPlayerColor);
    }
}
