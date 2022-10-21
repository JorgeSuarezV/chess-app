package chess.movement;

import chess.position.Board;
import chess.position.Move;

public interface MovementEvaluator {
    String isValidMove(Board board, Move move);

    void addMovementValidator(MovementEvaluator movementEvaluator);

}
