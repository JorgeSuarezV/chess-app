package chess.position;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Board extends Cloneable {

    @Nullable Position getPosition(Coordinate coordinate);

    Board clone();

    void addDefaultBoardPieces();
    void revertMove(Move move);

    void movePiece(Move move);

    List<List<Position>> getHistory();

    List<Position> getActualPositions();

    int getSize();
}
