package chess.piece;

import chess.movement.*;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PieceFactory {

    static int id = 0;

    public static Piece createRook(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        return new Piece(evaluators, illegalevaluators, true, color, "R" + id++, PieceType.ROOK);
    }

    public static Piece createBishop(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        return new Piece(evaluators, illegalevaluators, true, color, "B" + id++, PieceType.BISHOP);
    }

    public static Piece createQueen(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        return new Piece(evaluators,illegalevaluators , true, color, "Q" + id++, PieceType.QUEEN);
    }

    public static Piece createKnight(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new VariableJumpingMovementEvaluator(1,2));
        evaluators.add(new VariableJumpingMovementEvaluator(2,1));
        return new Piece(evaluators, illegalevaluators, true, color, "K" + id++, PieceType.KNIGHT);
    }

    public static Piece createKing(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

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
        return new Piece(evaluators, illegalevaluators, false, color, "E" + id++, PieceType.KING);
    }

    public static Piece createPawn(PlayerColor color){
        HashSet<MovementEvaluator> illegalevaluators = new HashSet<>();
        illegalevaluators.add(new GotCheckedMovementEvaluator());

        int vectorY = 1;
        if (color == PlayerColor.BLACK) vectorY = -1;
        MovementEvaluator evaluator1 = new UnidirectionalMovementEvaluator(1, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(1))
                .addMovementEvaluator(new TakeMovementEvaluator(true));
        MovementEvaluator evaluator2 = new UnidirectionalMovementEvaluator(-1, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(1))
                .addMovementEvaluator(new TakeMovementEvaluator(true));
        MovementEvaluator evaluator3 = new UnidirectionalMovementEvaluator(0, vectorY)
                .addMovementEvaluator(new LimitMovementEvaluator(2))
                .addMovementEvaluator(new TakeMovementEvaluator(false))
                .addMovementEvaluator(new MoveQuantityMovementEvaluator(0));
        MovementEvaluator evaluator4 = new UnidirectionalMovementEvaluator(0, vectorY)
                .addMovementEvaluator(new TakeMovementEvaluator(false))
                .addMovementEvaluator(new LimitMovementEvaluator(1));
        return new Piece(new HashSet<>(Arrays.asList(evaluator1, evaluator2, evaluator3, evaluator4)), illegalevaluators, true, color, "P" + id++, PieceType.PAWN);
    }
}
