package chess.movement;

import chess.position.Board;
import chess.position.Move;
import chess.position.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEvaluator implements MovementEvaluator{

    private final Set<MovementEvaluator> movementEvaluators = new HashSet<>();


    public String reachedPosition(Board board, Move move) {
        Position toPosition = board.getPosition(move.getTo());
        Position fromPosition = board.getPosition(move.getFrom());
        if (toPosition == null || fromPosition == null) return "invalid move";

        if (toPosition.getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else if (toPosition.getPiece().isTakeable(fromPosition.getPiece())) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else return "invalid move";
    }

    private String checkValidMoveWithEvaluators(Board board, Move move){
        for (MovementEvaluator movementEvaluator : movementEvaluators) {
            String result = movementEvaluator.isValidMove(board, move);
            if (!result.equals("ok")) return result;
        }
        return "ok";
    }

    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        movementEvaluators.add(movementEvaluator);
        return this;
    }
}
