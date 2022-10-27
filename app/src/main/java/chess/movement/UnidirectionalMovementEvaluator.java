package chess.movement;

import chess.exception.*;
import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;

import java.util.Set;

public class UnidirectionalMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{
    private final int vectorX;
    private final int vectorY;

    public UnidirectionalMovementEvaluator(int vectorX, int vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position positionToEvaluate = logicSetup(board, move);
        if (pathLogic(positionToEvaluate, move, board)){
            moves.add(move);
            return reachedPosition(board, move, moves);
        }
        throw new OutOfBoundsException();
    }

    private Position logicSetup(Board board, Move move) throws OutOfBoundsException {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (position1 == null || position2 == null) throw new OutOfBoundsException();

        int xFrom = position1.getX();
        int yFrom = position1.getY();
        return board.getPosition(new Coordinate(xFrom + vectorX, yFrom + vectorY));
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position positionToEvaluate = logicSetup(board, move);
        if (pathLogic(positionToEvaluate, move, board)) return checkTargetMoveWithEvaluators(board, move);
        throw new OutOfBoundsException();
    }

    private boolean pathLogic(Position positionToEvaluate, Move move, Board board) throws PathBlockedException {
        while (positionToEvaluate != null){
            Piece pieceInTarget = positionToEvaluate.getPiece();
            if (positionToEvaluate.getCoordinate().compareTo(move.getTo()) == 0) {
                return true;
            }else if (pieceInTarget != null) throw new PathBlockedException();
            else positionToEvaluate = board.getPosition(new Coordinate(positionToEvaluate.getX() + vectorX, positionToEvaluate.getY() + vectorY));
        }
        return false;
    }



}
