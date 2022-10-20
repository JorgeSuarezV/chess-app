package chess.movement;

import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.HashSet;
import java.util.Set;

public class DiagonalMovementValidator implements MovementEvaluator{

    private Set<MovementEvaluator> movementEvaluators = new HashSet<>();

    @Override
    public String isValidMove(Board board, Move move) {
        if (move.getFrom().getY() == move.getTo().getY()) return "invalid move";
        if (board.getPosition(move.getFrom()) == null || board.getPosition(move.getTo()) ==  null) return "invalid move";

        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        if (Math.abs(fromX-toX) != Math.abs(fromY-toY)) return "invalid move";

        int min = Math.min(fromX, toX);
        int max = Math.max(fromX, toX);

        int directionX = (toX - fromX) / (max - min);
        int directionY = (toY - fromY) / (max - min);

        //Check if there is a chess.piece in the way
        for (int i = 1; i < max ; i++) {
            Position position = board.getPosition(new Coordinate(fromX + i * directionX, fromY + i * directionY));
            if (position.getPiece() !=null) {
                return "invalid move";
            }
        }
        return reachedPosition(board, move);
    }

    public String reachedPosition(Board board, Move move) {
//        if (board.getPosition(move.getCoordinate()).getPiece() == null) {
//            return checkValidMoveWithEvaluators(board, move);
//        }
//        else if (board.getPosition(coordinate).getPiece().isTakeable()) {
//            return checkValidMoveWithEvaluators(board, move);
//        }
//        else
            return "invalid move";
    }

    @Override
    public void addMovementValidator(MovementEvaluator movementEvaluator) {

    }
}
