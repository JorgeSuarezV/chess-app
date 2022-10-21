package chess.movement;

import chess.position.Board;
import chess.position.Move;
import chess.position.Position;

public class TakeMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{
    @Override
    public String isValidMove(Board board, Move move) {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (position1 == null || position2 == null) return "invalid move";
        if (position1.getPiece() != null && position2.getPiece() != null && position2.getPiece().isTakeable(position1.getPiece())) return "ok";
        else return "invalid move";
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
