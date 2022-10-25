package chess.position;

import chess.piece.Piece;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static chess.util.Cloner.cloneBoard;
import static chess.util.Cloner.clonePositionList;

public class TestBoardImpl implements TestBoard {

    private List<List<Position>> history = new ArrayList<>();
    final int size;

    public TestBoardImpl(int size){
        this.size = size;
    }

    public TestBoardImpl(List<List<Position>> history, int size) {
        this.history = history;
        this.size = size;
    }

    private List<Position> generatePositions() {
        List<Position> positionList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                positionList.add(new Position(i,j));
            }
        }
        return positionList;
    }

    public @Nullable Position getPosition(Coordinate coordinate){
        List<Position> actualState = new ArrayList<>(history.get(history.size()-1));
        return getPosition(actualState, coordinate);
    }

    private @Nullable Position getPosition(List<Position> positions, Coordinate coordinate){
        for (Position position : positions) {
            if (position.getY() == coordinate.getY() && position.getX() == coordinate.getX())
                return position;
        }
        return null; // chess.position not in board
    }

    @Override
    public Board clone() {
        return new TestBoardImpl(cloneBoard(history), size);
    }

    @Override
    public void addDefaultBoardPieces() {
    }

    public void movePiece(Set<Move> moves){
        List<Position> actualPositions = getActualPositions();
        for (Move move : moves) {
            Position fromPosition = getPosition(actualPositions, move.getFrom());
            Position toPostion = getPosition(actualPositions, move.getTo());
            toPostion.setPiece(fromPosition.setPiece(null));
        }
        history.add(actualPositions);
    }

    @Override
    public List<List<Position>> getHistory() {
        return history;
    }

    @Override
    public List<Position> getActualPositions() {
        return clonePositionList(history.get(history.size() - 1));
    }

    @Override
    public int getHeight() {
        return size;
    }
    @Override
    public int getWidth() {
        return size;
    }



    public void addPiece(Coordinate coordinate, Piece piece){
        for (Position position : history.get(history.size()-1)) {
            if (position.getCoordinate().compareTo(coordinate) == 0) position.setPiece(piece);
        }
    }

    @Override
    public void removePiece(Coordinate coordinate) {
        for (List<Position> positions : history) {
            Position position = getPosition(positions, coordinate);
            if (position != null) position.setPiece(null);
        }
    }
}
