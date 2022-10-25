package chess.position;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public interface Board extends Cloneable {

    @Nullable Position getPosition(Coordinate coordinate);

    Board clone();

    void addDefaultBoardPieces();

    void movePiece(Set<Move> moves);

    List<List<Position>> getHistory();

    List<Position> getActualPositions();

    int getHeight();
    int getWidth();
}
