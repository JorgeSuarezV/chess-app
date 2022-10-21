package chess.movement;

import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

public class UnidirectionalMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{
    int vectorX;
    int vectorY;

    public UnidirectionalMovementEvaluator(int vectorX, int vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    @Override
    public String isValidMove(Board board, Move move) {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (position1 == null || position2 == null) return "invalid move";
        int xFrom = position1.getX();
        int yFrom = position1.getY();
        Position positionToEvaluate = board.getPosition(new Coordinate(xFrom + vectorX, yFrom + vectorY));
        while (positionToEvaluate != null){
            Piece pieceInTarget = positionToEvaluate.getPiece();
            if (positionToEvaluate.getCoordinate().compareTo(move.getTo()) == 0) {
                return reachedPosition(board, move);
            }else if (pieceInTarget != null) return "invalid move";
            else positionToEvaluate = board.getPosition(new Coordinate(positionToEvaluate.getX() + vectorX, positionToEvaluate.getY() + vectorY));
        }
        return "invalid move";
    }


    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
