package movement;

import position.Board;
import position.Coordinate;
import position.Move;

import java.util.HashSet;
import java.util.Set;

public class VerticalMovementEvaluator implements MovementEvaluator {

    private Set<MovementEvaluator> movementEvaluators = new HashSet<>();


    @Override
    public String isValidMove(Board board, Move move) {
        if (move.getFrom().getX() != move.getTo().getX()) return "invalid move";
        if (move.getFrom().getY() == move.getTo().getY()) return "invalid move";
        if (board.getPosition(move.getFrom()) == null || board.getPosition(move.getTo()) == null) return "invalid move";
        Coordinate coordinate;
        if (move.getFrom().getY() < move.getTo().getY()) {
            coordinate = move.getFrom().addYOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() != null) {
                coordinate = coordinate.addYOne();
            }
            return reachedPosition(board, coordinate, move);
        } else {
            coordinate = move.getFrom().takeYOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() != null) {
                coordinate = coordinate.takeYOne();
            }
        }
            return reachedPosition(board, coordinate, move);
    }


//        Coordinate positionEvaluated = move.getFrom().takeYOne();
//        while (board.getPosition(positionEvaluated) != null && board.getPosition(positionEvaluated).getPiece() == null){
//            positionEvaluated = positionEvaluated.takeYOne();
//        }
//        if (board.getPosition(positionEvaluated) != null && board.getPosition(positionEvaluated).getPiece().isTakeable())
//
//
//        positionEvaluated = move.getFrom().addYOne();
//
//        while (board.getPosition(positionEvaluated) == null){
//            positionEvaluated = positionEvaluated.addYOne();
//        }
//        return board.getPosition(positionEvaluated).getPiece().isTakeable();


    public String reachedPosition(Board board, Coordinate coordinate, Move move) {
        if (board.getPosition(coordinate).getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else if (board.getPosition(coordinate).getPiece().isTakeable()) {
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
