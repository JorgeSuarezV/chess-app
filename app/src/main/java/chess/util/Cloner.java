package chess.util;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Cloner {

    public static List<Position> clonePositionList(List<Position> originalArrayList) {
        List<Position> copyArrayofList = new ArrayList<>(originalArrayList.size());
        for (Position item : originalArrayList) copyArrayofList.add(item.clone());
        return copyArrayofList;
    }

    public static List<List<Position>> cloneBoard(List<List<Position>> history) {
        List<List<Position>> copyArrayOfList = new ArrayList<>(history.size());
        for (List<Position> positions : history) {
            copyArrayOfList.add(clonePositionList(positions));
        }
        return copyArrayOfList;
    }
}
