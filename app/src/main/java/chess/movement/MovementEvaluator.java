package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;

public interface MovementEvaluator {
    boolean isValidMove(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException;
    boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException;

    MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator);

}
