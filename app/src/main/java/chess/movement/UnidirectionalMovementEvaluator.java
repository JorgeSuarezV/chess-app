package chess.movement;

import chess.exception.*;
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
    public boolean isValidMove(Board board, Move move) throws OutOfBoundsException, NotMovingAPieceException, MovementException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position positionToEvaluate = logicSetup(board, move);
        if (pathLogic(positionToEvaluate, move, board))
            return reachedPosition(board, move);
        throw new OutOfBoundsException("From or To position OOB");
    }

    private Position logicSetup(Board board, Move move) throws OutOfBoundsException {
        Position position1 = board.getPosition(move.getFrom());
        Position position2 = board.getPosition(move.getTo());
        if (position1 == null || position2 == null) throw new OutOfBoundsException("From or To position OOB");

        int xFrom = position1.getX();
        int yFrom = position1.getY();
        return board.getPosition(new Coordinate(xFrom + vectorX, yFrom + vectorY));
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        Position positionToEvaluate = logicSetup(board, move);
        if (pathLogic(positionToEvaluate, move, board)) return checkTargetMoveWithEvaluators(board, move);
        throw new OutOfBoundsException("From or To position OOB");
    }

    private boolean pathLogic(Position positionToEvaluate, Move move, Board board) throws PathBlockedException {
        while (positionToEvaluate != null){
            Piece pieceInTarget = positionToEvaluate.getPiece();
            if (positionToEvaluate.getCoordinate().compareTo(move.getTo()) == 0) {
                return true;
            }else if (pieceInTarget != null) throw new PathBlockedException("a piece blocks the way");
            else positionToEvaluate = board.getPosition(new Coordinate(positionToEvaluate.getX() + vectorX, positionToEvaluate.getY() + vectorY));
        }
        return false;
    }


    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return super.addMovementEvaluator(movementEvaluator);
    }
}
