package chess.movement;

import chess.exception.*;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathTakeMovementEvaluator extends AbstractEvaluator implements MovementEvaluator{

    int xVector;
    int yVector;
    int xSign;
    int ySign;

    public PathTakeMovementEvaluator(int xVector, int yVector) {
        if (Math.abs(xVector) != Math.abs(yVector)) throw new RuntimeException();
        xSign = xVector < 1 ? -1 : 1;
        ySign = yVector < 1 ? -1 : 1;
        this.xVector = xVector;
        this.yVector = yVector;
    }

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        MovementEvaluator finalCoordinateME = new VariableJumpingMovementEvaluator(xVector, yVector)
                .addMovementEvaluator(new TakeMovementEvaluator(false));
        moves.addAll(finalCoordinateME.isValidMove(board,move,moves));
        List<MovementEvaluator> evaluators  = new ArrayList<>();
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 1; i < Math.abs(xVector); i++) {
            evaluators.add(new VariableJumpingMovementEvaluator(i*xSign,i*ySign));
            coordinates.add(new Coordinate(move.getFrom().getX() + i*xSign, move.getFrom().getY() + i*ySign));
        }
        for (int i = 0; i < evaluators.size() ; i++) {
            Move move1 = new Move(move.getFrom(), coordinates.get(i));
            evaluators.get(i).isValidMove(board, move1,moves);
            checkValidMoveWithEvaluators(board, move1, moves);
        }
        return checkValidMoveWithEvaluators(board, move, moves);
    }

    @Override
    public boolean isThreatening(Board board, Move move) throws MovementException, NotMovingAPieceException, OutOfBoundsException, CannotTakePieceException, PathBlockedException, SelfCheckException {
        MovementEvaluator finalCoordinateME = new VariableJumpingMovementEvaluator(xVector, yVector)
                .addMovementEvaluator(new TakeMovementEvaluator(false));
        finalCoordinateME.isThreatening(board,move);
        List<MovementEvaluator> evaluators  = new ArrayList<>();
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 1; i < Math.abs(xVector); i++) {
            evaluators.add(new VariableJumpingMovementEvaluator(i*xSign,i*ySign));
            coordinates.add(new Coordinate(move.getFrom().getX() + i*xSign, move.getFrom().getY() + i*ySign));
        }
        for (int i = 0; i < evaluators.size() ; i++) {
            if (evaluators.get(i).isThreatening(board,new Move(move.getFrom(), coordinates.get(i))))
                checkTargetMoveWithEvaluators(board, new Move(move.getFrom(), coordinates.get(i)));
        }
        return true;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        super.addMovementEvaluator(movementEvaluator);
        return this;
    }
}
