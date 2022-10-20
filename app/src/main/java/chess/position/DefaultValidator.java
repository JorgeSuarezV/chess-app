package position;

import movement.MovementEvaluator;
import piece.Piece;

public class DefaultValidator implements Validator{


    @Override
    public String checkMove(Board board, Move move) {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());

        if (fromPosition == null || toPosition == null) return "Not in bounds";

        Piece piece = fromPosition.getPiece();
        if (piece == null) return "Not moving a piece";

        boolean atLeastOneValid = false;
        for (MovementEvaluator summativeMovementEvaluator : piece.getSummativeMovementEvaluators()) {
            if (summativeMovementEvaluator.isValidMove(board, move).equals("ok")) {
                atLeastOneValid = true;
                break;
            }
        }
        if (!atLeastOneValid) return "invalid move";

        for (MovementEvaluator movementEvaluator : piece.getRestrictiveMovementEvaluator()) {
            if (!(movementEvaluator.isValidMove(board, move).equals("ok"))) return "invalid move";
        }

        return "ok";
    }

}
