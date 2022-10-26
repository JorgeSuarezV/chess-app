package chess.piece;

import chess.movement.*;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.HashSet;
import java.util.Set;

import static chess.movement.MovementEvaluatorSetFactory.*;

public class PieceFactory {

    static int id = 0;

    public static Piece createRook(PlayerColor color){
        return new Piece(createRookMovementEvaluators(), createGeneralIllegalMovementEvaluators(), true, color, "R" + id++, PieceType.ROOK);
    }

    public static Piece createBishop(PlayerColor color){
        return new Piece(createBishopMovementEvaluators(), createGeneralIllegalMovementEvaluators(), true, color, "B" + id++, PieceType.BISHOP);
    }

    public static Piece createQueen(PlayerColor color){
        return new Piece(createQueenMovementEvaluators(),createGeneralIllegalMovementEvaluators() , true, color, "Q" + id++, PieceType.QUEEN);
    }

    public static Piece createKnight(PlayerColor color){
        return new Piece(createKnightMovementEvaluators(), createGeneralIllegalMovementEvaluators(), true, color, "K" + id++, PieceType.KNIGHT);
    }

    public static Piece createKing(PlayerColor color, int boardSize){
        return new Piece(createKingMovementEvaluators(color, boardSize), createGeneralIllegalMovementEvaluators(), false, color, "E" + id++, PieceType.KING);
    }

    public static Piece createPawn(PlayerColor color){
        return new Piece(createPawnMovementEvaluators(color), createGeneralIllegalMovementEvaluators(), true, color, "P" + id++, PieceType.PAWN);
    }

    public static Piece createChancellor(PlayerColor color){
        return new Piece(createChancellorMovementEvaluators(), createGeneralIllegalMovementEvaluators(), true, color, "C" + id++, PieceType.CHANCELLOR);
    }

    public static Piece createArchbishop(PlayerColor color){
        return new Piece(createArchbishopMovementEvaluators(), createGeneralIllegalMovementEvaluators(), true, color, "C" + id++, PieceType.ARCHBISHOP);
    }



//    public static Piece createAntiPawn(PlayerColor color){
//        return new Piece(createAntiPawnMovementEvaluators(color), createGeneralIllegalMovementEvaluators(), true,color, "A" + id++, PieceType.KING);
//    }
}
