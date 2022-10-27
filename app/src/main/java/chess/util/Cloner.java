package chess.util;

import chess.piece.Piece;
import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Cloner {

    public static List<Position> clonePositionList(List<Position> originalArrayList) {
        List<Position> copyArrayofList = new ArrayList<>(originalArrayList.size());
        for (Position item : originalArrayList) copyArrayofList.add(clonePosition(item));
        return copyArrayofList;
    }

    public static List<List<Position>> cloneBoard(List<List<Position>> history) {
        List<List<Position>> copyArrayOfList = new ArrayList<>(history.size());
        for (List<Position> positions : history) {
            copyArrayOfList.add(clonePositionList(positions));
        }
        return copyArrayOfList;
    }
    public static Position clonePosition(Position position){
        return new Position(position.getCoordinate(), clonePiece(position.getPiece()));
    }

    public static Piece clonePiece(Piece piece){
        if (piece == null) return null;
        return new Piece(piece.getMovementEvaluators(), piece.getIllegalMovementEvaluators(), piece.isTakeable(), piece.getPlayerColor(), piece.getName(), piece.getType());
    }
}
