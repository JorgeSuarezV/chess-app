package chess.position;

import chess.movement.MovementEvaluator;
import chess.piece.Piece;

import java.util.Set;

public class ClassicValidator implements Validator{

    Set<MovementEvaluator> gameEvaluators;

    public ClassicValidator(Set<MovementEvaluator> gameEvaluators) {
        this.gameEvaluators = gameEvaluators;
    }

    @Override
    public String checkMove(Board board, Move move) {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());

        if (fromPosition == null || toPosition == null) return "Not in bounds";

        Piece piece = fromPosition.getPiece();
        if (piece == null) return "Not moving a piece";

        for (MovementEvaluator summativeMovementEvaluator : piece.getSummativeMovementEvaluators()) {
            if (summativeMovementEvaluator.isValidMove(board, move).equals("ok")) return "ok";
        }
        return "invalid move";
    }

}
