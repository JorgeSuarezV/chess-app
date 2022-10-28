package chess.position;

import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public interface Board extends Cloneable {
    @Nullable
    Position getPosition(Coordinate coordinate);

    Board clone();

    Board addDefaultBoardPieces();

    void movePiece(Set<Move> moves);

    List<List<Position>> getHistory();

    List<Position> getActualPositions();

    int getHeight();
    int getWidth();
}
