package chess.piece;

import chess.movement.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static chess.piece.Color.BLACK;

public class PieceFactory {

    static int id = 0;

    public static Piece createRook(Color color){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        return new Piece(evaluators, true, color, "R" + id++, PieceType.ROOK);
    }

    public static Piece createBishop(Color color){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        return new Piece(evaluators, true, color, "B" + id++, PieceType.BISHOP);
    }

    public static Piece createQueen(Color color){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        return new Piece(evaluators, true, color, "Q" + id++, PieceType.QUEEN);
    }

    public static Piece createKnight(Color color){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new VariableJumpingMovementEvaluator(1,2));
        evaluators.add(new VariableJumpingMovementEvaluator(2,1));
        return new Piece(evaluators, true, color, "K" + id++, PieceType.KNIGHT);
    }

    public static Piece createKing(Color color){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        for (MovementEvaluator movementEvaluator : evaluators) {
            movementEvaluator.addMovementEvaluator(new LimitMovementEvaluator(1));
        }
        return new Piece(evaluators, false, color, "E" + id++, PieceType.KING);
    }

    public static Piece createPawn(Color color){
        int vectorY = 1;
        if (color == BLACK) vectorY = -1;
        MovementEvaluator evaluator1 = new UnidirectionalMovementEvaluator(1, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(1))
                .addMovementEvaluator(new TakeMovementEvaluator());
        MovementEvaluator evaluator2 = new UnidirectionalMovementEvaluator(-1, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(1))
                .addMovementEvaluator(new TakeMovementEvaluator());
        MovementEvaluator evaluator3 = new UnidirectionalMovementEvaluator(0, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(2))
                .addMovementEvaluator(new MoveQuantityMovementEvaluator(0));
        MovementEvaluator evaluator4 = new UnidirectionalMovementEvaluator(0, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(1));
        return new Piece(new HashSet<>(Arrays.asList(evaluator1, evaluator2, evaluator3, evaluator4)), true, color, "P" + id++, PieceType.PAWN);
    }
}
