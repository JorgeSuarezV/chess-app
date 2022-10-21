package chess.movement;

import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

public class VariableJumpingMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{
    int[][] movements;

    public VariableJumpingMovementEvaluator(int var1, int var2) {
        this.movements = new int[][]{{-var1, -var2}, {var1, -var2}, {-var1, var2}, {var1, var2}};
    }

    @Override
    public String isValidMove(Board board, Move move) {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());
        if (fromPosition == null || toPosition == null || fromPosition.getPiece() == null) return "invalid move";
        Piece piece = fromPosition.getPiece();
        int xFrom = move.getFrom().getX();
        int yFrom = move.getFrom().getY();

        for (int[] movement : movements) {
            Position endPosition = board.getPosition(new Coordinate(xFrom + movement[0], yFrom + movement[1]));
            if (endPosition != null && toPosition.compareTo(endPosition) == 0) return reachedPosition(board, move);
        }
        return "invalid move";
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
