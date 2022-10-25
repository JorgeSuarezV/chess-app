package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Move;

import java.util.Set;

public interface MovementEvaluator {
    Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException;

    boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException;

    MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator);

}
