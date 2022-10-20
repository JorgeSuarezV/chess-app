package movement;

import position.Board;
import position.Move;

public interface MovementEvaluator {
    String isValidMove(Board board, Move move);

    void addMovementValidator(MovementEvaluator movementEvaluator);

}
