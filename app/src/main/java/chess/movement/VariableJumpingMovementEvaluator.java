package chess.movement;

import chess.exception.*;
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
    public boolean isValidMove(Board board, Move move) throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        if (VariableJumpingLogic(board, move)) return reachedPosition(board, move);
        throw new MovementException("The piece can not move like that");
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        if (VariableJumpingLogic(board, move)) return checkTargetMoveWithEvaluators(board, move);
        throw new MovementException("The piece can not move like that");
    }

    private boolean VariableJumpingLogic(Board board, Move move) throws OutOfBoundsException {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());
        if (fromPosition == null || toPosition == null || fromPosition.getPiece() == null) throw new OutOfBoundsException("From or To position OOB");

        int xFrom = move.getFrom().getX();
        int yFrom = move.getFrom().getY();

        for (int[] movement : movements) {
            Position endPosition = board.getPosition(new Coordinate(xFrom + movement[0], yFrom + movement[1]));
            if (endPosition != null && toPosition.compareTo(endPosition) == 0) return true;
        }
        return false;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
