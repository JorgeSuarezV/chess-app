package chess.position;

import chess.piece.Piece;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestBoard implements Board {
    private final List<List<Position>> history;
    private final Validator validator;

    public TestBoard(Validator validator) {
        this.validator = validator;
        List<List<Position>> history1 = new ArrayList<>();
        history1.add(generatePositions());
        history = history1;
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
    public void addDefaultBoardPieces() {
    }

    public void addPieces(Map<Coordinate, Piece> map) {
        for (Coordinate coordinate : map.keySet()) {
            addPiece(coordinate, map.get(coordinate));
        }
    }

    public String makeMove(Move move) { // TODO move to Game class
        String result = validator.checkMove(this, move);
        if (result.equals("ok")) {
            movePiece(move);
            return "ok";
        }
        return result;
    }

    private void addPiece(Coordinate coordinate, Piece piece){
        for (Position position : history.get(history.size()-1)) {
            if (position.getCoordinate().compareTo(coordinate) != 1) position.setPiece(piece);
        }
    }

    void movePiece(Move move){
        Position fromPosition = getPosition(getActualPositions(), move.getFrom());
        Position toPostion = getPosition(getActualPositions(), move.getTo());
        toPostion.setPiece(fromPosition.setPiece(null));
        history.add(getActualPositions());
    }

    @Override
    public List<List<Position>> getHistory() {
        return history;
    }

    @Override
    public List<Position> getActualPositions() {
        return new ArrayList<>(history.get(history.size()-1));
    }


    @Override
    public int getSize() {
        return 8;
    }
}
