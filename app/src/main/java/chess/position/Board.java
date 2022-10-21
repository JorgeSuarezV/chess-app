package chess.position;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Board {

    @Nullable Position getPosition(Coordinate coordinate);

    void addDefaultBoardPieces();

    String makeMove(Move move);

    List<List<Position>> getHistory();

    List<Position> getActualPositions();

    int getSize();
}
