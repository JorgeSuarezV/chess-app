package chess.movement;

import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;

import java.util.HashSet;
import java.util.Set;

public class HorizontalMovementEvaluator implements MovementEvaluator {

    private Set<MovementEvaluator> movementEvaluators = new HashSet<>();

    @Override
    public String isValidMove(Board board, Move move) {
        if (move.getFrom().getY() != move.getTo().getY()) return "invalid move";
        if (move.getFrom().getX() == move.getTo().getX()) return "invalid move";
        if (board.getPosition(move.getFrom()) == null || board.getPosition(move.getTo()) ==  null) return "invalid move";
        Coordinate coordinate;
        if (move.getFrom().getX() < move.getTo().getX()){
            coordinate = move.getFrom().addXOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() == null){
                coordinate = coordinate.addXOne();
            }
        }else {
            coordinate = move.getFrom().takeXOne();
            while (coordinate.compareTo(move.getTo()) != 0 && board.getPosition(coordinate).getPiece() == null){
                coordinate = coordinate.takeXOne();
            }
        }
        if (coordinate.compareTo(move.getTo())!= 0) return "invalid move";
        return reachedPosition(board, move);

    }

    public String reachedPosition(Board board, Move move) {
        if (board.getPosition(move.getTo()).getPiece() == null) {
            return checkValidMoveWithEvaluators(board, move);
        }
        else if (board.getPosition(move.getTo()).getPiece().isTakeable(board.getPosition(move.getFrom()).getPiece())) {
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
