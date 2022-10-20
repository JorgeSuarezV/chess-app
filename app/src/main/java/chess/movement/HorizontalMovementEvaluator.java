package movement;

import position.Board;
import position.Coordinate;
import position.Move;

import java.util.HashSet;
import java.util.Set;

public class HorizontalMovementEvaluator implements MovementEvaluator {

    private Set<MovementEvaluator> movementEvaluators = new HashSet<>();

    @Override
    public String isValidMove(Board board, Move move) {
        if (move.getFrom().getY() != move.getTo().getY()) return "invalid move";
        if (move.getFrom().getX() == move.getTo().getX()) return "invalid move";
        if (board.getPosition(move.getFrom()) == null || board.getPosition(move.getTo()) ==  null) return "invalid move";
        if (move.getFrom().getX() < move.getTo().getX()){
            Coordinate coordinate = move.getFrom().addXOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() != null){
                coordinate = coordinate.addXOne();
            }
            return reachedPosition(board, coordinate,move);
        }else {
            Coordinate coordinate = move.getFrom().takeXOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() != null){
                coordinate = coordinate.takeXOne();
            }
            String result = reachedPosition(board, coordinate, move);
            if (result.equals("ok")) return checkValidMoveWithEvaluators(board, move);
            else return result;
        }

    }

    public String reachedPosition(Board board, Coordinate coordinate, Move move) {
        if (board.getPosition(coordinate).getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else if (board.getPosition(coordinate).getPiece().isTakeable(board.getPosition(move.getFrom()).getPiece())) {
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

    @Override
    public void addMovementValidator(MovementEvaluator movementEvaluator) {
        movementEvaluators.add(movementEvaluator);
    }

}
