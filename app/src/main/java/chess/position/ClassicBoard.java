package chess.position;

import chess.piece.Color;
import chess.piece.Piece;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static chess.piece.PieceFactory.*;

public class ClassicBoard implements Board {
    private final List<List<Position>> history;
    private final Validator validator = new DefaultValidator();

    public ClassicBoard() {
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
        addPiece(new Coordinate(0,0), createRook(Color.WHITE));
        addPiece(new Coordinate(7,0), createRook(Color.WHITE));
        addPiece(new Coordinate(0,7), createRook(Color.BLACK));
        addPiece(new Coordinate(7,7), createRook(Color.BLACK));
        addPiece(new Coordinate(2, 0), createBishop(Color.WHITE));
        addPiece(new Coordinate(5, 0), createBishop(Color.WHITE));
        addPiece(new Coordinate(2,7), createBishop(Color.BLACK));
        addPiece(new Coordinate(5,7), createBishop(Color.BLACK));
        addPiece(new Coordinate(3, 0), createQueen(Color.WHITE));
        addPiece(new Coordinate(3, 7), createQueen(Color.BLACK));
    }

    public String makeMove(Move move) {  // TODO validator class?
        String result = validator.checkMove(this, move);
        if (result.equals("ok")) {
            movePiece(move);
            return "ok";
        }
        return result;
    }

    private void addPiece(Coordinate coordinate, Piece piece){
        for (Position position : history.get(history.size()-1)) {
            if (position.getCoordinate().compareTo(coordinate) == 1) continue;
            else position.setPiece(piece);
        }
    }

    void movePiece(Move move){
        List<Position> actualPositions = getActualPositions();
        Position fromPosition = getPosition(actualPositions, move.getFrom());
        Position toPostion = getPosition(actualPositions, move.getTo());
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
