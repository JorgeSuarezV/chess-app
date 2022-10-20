package piece;

import movement.HorizontalMovementEvaluator;
import movement.MovementEvaluator;
import movement.VerticalMovementEvaluator;

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
}
