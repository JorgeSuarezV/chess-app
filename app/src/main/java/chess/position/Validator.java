package chess.position;

import chess.exception.*;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.dissis.chess.gui.PlayerColor;

public interface Validator {
    MoveResult checkMove(
        Board board,
        Move move,
        PlayerColor currentPlayerColor
    )
        throws OutOfBoundsException, NotMovingAPieceException, MovementException, PathBlockedException, CannotTakePieceException, SelfCheckException;
}
