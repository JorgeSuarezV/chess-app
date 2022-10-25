package chess.movement;

import chess.exception.*;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.Set;

public class CastleMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{
    
    int yCoordinate;

    MovementEvaluator moveQuantityEvaluator = new MoveQuantityMovementEvaluator(0);

    public CastleMovementEvaluator(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position fromPosition = board.getPosition(move.getFrom());
        Position toPosition = board.getPosition(move.getTo());
        if (fromPosition == null || toPosition == null) throw new OutOfBoundsException();
        Piece king = fromPosition.getPiece();
        if (king == null) throw new NotMovingAPieceException();

        if (move.getTo().getY() != move.getFrom().getY() || move.getFrom().getY() != yCoordinate) throw new MovementException(); // que este en el inicio en y
        moveQuantityEvaluator.isValidMove(board, move, moves);
        int xMove = move.getTo().getX() - move.getFrom().getX();
        if (xMove == -2){ // esta para la izquierda
            Coordinate rookFrom = new Coordinate(0, yCoordinate);
            Position rookTo = board.getPosition(new Coordinate(fromPosition.getCoordinate().getX() - 1, yCoordinate));
            Position position = board.getPosition(rookFrom);
            if (rookTo != null && rookTo.getPiece() != null) throw new MovementException();
            if (position != null && position.getPiece().getType() == PieceType.ROOK) { // hay un rook en la izquierda
                Move rookMove = new Move(rookFrom, new Coordinate(move.getFrom().getX() - 1, yCoordinate));
                moveQuantityEvaluator.isValidMove(board, rookMove, moves);
                checkMovementEvaluators(board, rookMove, moves, position.getPiece());
                moves.add(rookMove);
                return moves;
            }
        }else if (xMove == 2){
            Coordinate rookFrom = new Coordinate(board.getSize() -1, yCoordinate);
            Position rookTo = board.getPosition(new Coordinate(fromPosition.getCoordinate().getX() + 1, yCoordinate));
            Position position = board.getPosition(rookFrom);
            if (rookTo != null && rookTo.getPiece() != null) throw new MovementException();
            if (position != null && position.getPiece().getType() == PieceType.ROOK) { // hay un rook en la izquierda
                Move rookMove = new Move(rookFrom, new Coordinate(move.getFrom().getX() + 1, yCoordinate));
                moves.addAll(moveQuantityEvaluator.isValidMove(board, rookMove, moves));
                checkMovementEvaluators(board, rookMove, moves, position.getPiece());
                moves.add(rookMove);
                return moves;
            }
        }
        throw new MovementException();
    }

    @Override
    public boolean isThreatening(Board board, Move move) {
        return false;
    }

    private void checkMovementEvaluators(Board board,Move move, Set<Move> moves, Piece piece) throws CannotTakePieceException, SelfCheckException, MovementException {
        boolean atLeastOneTrue = false;
        for (MovementEvaluator summativeMovementEvaluator : piece.getMovementEvaluators()) {
            try {
                if (summativeMovementEvaluator.isValidMove(board, move, moves).size() != 0) {
                    atLeastOneTrue = true;
                    break;
                }
            }catch (OutOfBoundsException|NotMovingAPieceException|MovementException|PathBlockedException ignored){
            }
        }
        if (!atLeastOneTrue) throw new MovementException();
    }
}
