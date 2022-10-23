package chess.movement;

import chess.exception.*;
import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.List;

public class MoveQuantityMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    int maxMove;

    public MoveQuantityMovementEvaluator(int maxMove) {
        this.maxMove = maxMove;
    }

    @Override
    public boolean isValidMove(Board board, Move move) throws NotMovingAPieceException, MovementException {
        Position position = board.getPosition(move.getFrom());
        if (position == null) throw new NotMovingAPieceException("invalid starting position");
        Piece piece = position.getPiece();
        if (piece == null) throw new NotMovingAPieceException("invalid starting position");
        if (!(countMovements(board, move.getFrom(), piece) <= maxMove)) throw new MovementException("The piece can not move like that");
        return true;
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException {
        return isValidMove(board, move);
    }


    private int countMovements(Board board, Coordinate coordinate, Piece piece){
        List<List<Position>> history = board.getHistory();
        int count = 0;
        for (int i = history.size() - 1; i >= 0; i--) {
            List<Position> positions = history.get(i);
            for (Position position : positions) {
                if (position.getPiece() == null) continue;
                if (piece.compareTo(position.getPiece()) == 0){
                    if (position.getCoordinate().compareTo(coordinate) == 0) break;
                    else {
                        coordinate = position.getCoordinate();
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
