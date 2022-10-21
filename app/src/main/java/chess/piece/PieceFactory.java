package chess.piece;

import chess.movement.DiagonalMovementEvaluator;
import chess.movement.HorizontalMovementEvaluator;
import chess.movement.MovementEvaluator;
import chess.movement.VerticalMovementEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    static int id = 0;

    public static Piece createRook(Color color){
        List<MovementEvaluator> evaluators = new ArrayList<>();
        evaluators.add(new HorizontalMovementEvaluator());
        evaluators.add(new VerticalMovementEvaluator());
        return new Piece(evaluators, new ArrayList<>(), true, color, "R" + id++);
    }

    public static Piece createBishop(Color color){
        List<MovementEvaluator> evaluators = new ArrayList<>();
        evaluators.add(new DiagonalMovementEvaluator());
        return new Piece(evaluators, new ArrayList<>(), true, color, "B" + id++);
    }

    public static Piece createQueen(Color color){
        List<MovementEvaluator> evaluators = new ArrayList<>();
        evaluators.add(new HorizontalMovementEvaluator());
        evaluators.add(new VerticalMovementEvaluator());
        evaluators.add(new DiagonalMovementEvaluator());
        return new Piece(evaluators, new ArrayList<>(), true, color, "Q" + id++);
    }
}
