package chess.movement;

import chess.exception.MovementException;
import chess.exception.NotMovingAPieceException;
import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoveQuantityMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    int maxMove;

    public MoveQuantityMovementEvaluator(int maxMove) {
        this.maxMove = maxMove;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws NotMovingAPieceException, MovementException {
        Position position = board.getPosition(move.getFrom());
        if (position == null) throw new NotMovingAPieceException("invalid starting position");
        Piece piece = position.getPiece();
        if (piece == null) throw new NotMovingAPieceException("invalid starting position");
        if (!(countMovements(board, move.getFrom(), piece) <= maxMove)) throw new MovementException();
        moves.add(move);
        return moves;
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException {
        HashSet<Move> moves = new HashSet<>();
        moves.add(move);
        return isValidMove(board, move, moves).size() != 0;
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


}
