package chess.movement;

import chess.piece.Piece;
import chess.piece.PieceType;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MovementEvaluatorSetFactory {


    public static Set<MovementEvaluator> createGeneralIllegalMovementEvaluators(){
        HashSet<MovementEvaluator> illegalEvaluators = new HashSet<>();
        illegalEvaluators.add(new GotCheckedMovementEvaluator());
        return illegalEvaluators;
    }

    public static Set<MovementEvaluator> createBishopMovementEvaluators(){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,1));
        evaluators.add(new UnidirectionalMovementEvaluator(-1,-1));
        evaluators.add(new UnidirectionalMovementEvaluator(1,-1));
        return evaluators;
    }

    public static Set<MovementEvaluator> createMusketeerMovementEvaluator(){
        Set<MovementEvaluator> evaluators = createBishopMovementEvaluators();
        evaluators.add(new RangeMovementEvaluator(1, 2, Set.of(PieceType.PAWN)));
        return evaluators;
    }

    public static Set<MovementEvaluator> createQueenMovementEvaluators(){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.addAll(createBishopMovementEvaluators());
        evaluators.addAll(createRookMovementEvaluators());
        return evaluators;
    }

    public static Set<MovementEvaluator> createRookMovementEvaluators() {
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new UnidirectionalMovementEvaluator(1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(-1, 0));
        evaluators.add(new UnidirectionalMovementEvaluator(0, 1));
        evaluators.add(new UnidirectionalMovementEvaluator(0, -1));
        return evaluators;
    }

        public static Set<MovementEvaluator> createKingMovementEvaluators(PlayerColor color, int boardSize) {
        Set<MovementEvaluator> evaluators = createQueenMovementEvaluators();
        if (color == PlayerColor.WHITE) evaluators.add(new CastleMovementEvaluator(0));
        else evaluators.add(new CastleMovementEvaluator(boardSize - 1));
        for (MovementEvaluator movementEvaluator : evaluators) {
            movementEvaluator.addMovementEvaluator(new LimitMovementEvaluator(1));
        }
        return evaluators;
    }

    public static Set<MovementEvaluator> createKnightMovementEvaluators(){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.add(new VariableJumpingMovementEvaluator(1,2));
        evaluators.add(new VariableJumpingMovementEvaluator(1,-2));
        evaluators.add(new VariableJumpingMovementEvaluator(-1,2));
        evaluators.add(new VariableJumpingMovementEvaluator(-1,-2));
        evaluators.add(new VariableJumpingMovementEvaluator(2,1));
        evaluators.add(new VariableJumpingMovementEvaluator(2,-1));
        evaluators.add(new VariableJumpingMovementEvaluator(-2,1));
        evaluators.add(new VariableJumpingMovementEvaluator(-2,-1));
        return evaluators;
    }



    public static Set<MovementEvaluator> createPawnMovementEvaluators(PlayerColor color){
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

    return new HashSet<>(Arrays.asList(evaluator1, evaluator2, evaluator3, evaluator4));
    }

    public static Set<MovementEvaluator> createArchbishopMovementEvaluators(){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.addAll(createKnightMovementEvaluators());
        evaluators.addAll(createBishopMovementEvaluators());
        return evaluators;
    }

    public static Set<MovementEvaluator> createChancellorMovementEvaluators(){
        Set<MovementEvaluator> evaluators = new HashSet<>();
        evaluators.addAll(createKnightMovementEvaluators());
        evaluators.addAll(createRookMovementEvaluators());
        return evaluators;
    }


//        public static Set<MovementEvaluator> createAntiPawnMovementEvaluators(PlayerColor color){
//        int vectorY = 1;
//        if (color == PlayerColor.BLACK) vectorY = -1;
//        Set<MovementEvaluator> evaluators = new HashSet<>();
//        evaluators.add(new PathTakeMovementEvaluator(-3, 3*vectorY)
//                .addMovementEvaluator(new MoveQuantityMovementEvaluator(0)));
//        evaluators.add(new PathTakeMovementEvaluator(3,3*vectorY)
//                .addMovementEvaluator(new MoveQuantityMovementEvaluator(0)));
//        evaluators.add(new VariableJumpingMovementEvaluator(-1, vectorY)
//                .addMovementEvaluator(new TakeMovementEvaluator(false)));
//        evaluators.add(new VariableJumpingMovementEvaluator(1, vectorY)
//                .addMovementEvaluator(new TakeMovementEvaluator(false)));
//        evaluators.add(new VariableJumpingMovementEvaluator(0, vectorY)
//                .addMovementEvaluator(new TakeMovementEvaluator(true)));
//        return evaluators;
//    }
}
