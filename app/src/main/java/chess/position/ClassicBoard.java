package chess.position;


import chess.piece.Piece;
import edu.austral.dissis.chess.gui.PlayerColor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static chess.piece.PieceFactory.*;
import static chess.util.Cloner.cloneBoard;
import static chess.util.Cloner.clonePositionList;


public class ClassicBoard implements Board {
    private final List<List<Position>> history;

    final int size;

    public ClassicBoard(int size) {
        this.size = size;
        List<List<Position>> history1 = new ArrayList<>();
        history1.add(generatePositions());
        history = history1;
    }

    private ClassicBoard(List<List<Position>> history, int size) {
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

    @Override
    public Board clone() {
        return new ClassicBoard(cloneBoard(history), 8);
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
        addPiece(new Coordinate(0,0), createRook(PlayerColor.WHITE));
        addPiece(new Coordinate(7,0), createRook(PlayerColor.WHITE));
        addPiece(new Coordinate(0,7), createRook(PlayerColor.BLACK));
        addPiece(new Coordinate(7,7), createRook(PlayerColor.BLACK));

        addPiece(new Coordinate(1,0), createKnight(PlayerColor.WHITE));
        addPiece(new Coordinate(6,0), createKnight(PlayerColor.WHITE));
        addPiece(new Coordinate(6,7), createKnight(PlayerColor.BLACK));
        addPiece(new Coordinate(1,7), createKnight(PlayerColor.BLACK));

        addPiece(new Coordinate(2, 0), createBishop(PlayerColor.WHITE));
        addPiece(new Coordinate(5, 0), createBishop(PlayerColor.WHITE));
        addPiece(new Coordinate(2,7), createBishop(PlayerColor.BLACK));
        addPiece(new Coordinate(5,7), createBishop(PlayerColor.BLACK));

        addPiece(new Coordinate(3, 0), createQueen(PlayerColor.WHITE));
        addPiece(new Coordinate(3, 7), createQueen(PlayerColor.BLACK));

        addPiece(new Coordinate(4, 0), createKing(PlayerColor.WHITE));
        addPiece(new Coordinate(4, 7), createKing(PlayerColor.BLACK));

        for (int i = 0; i < 8; i++) {
            addPiece(new Coordinate(i, 1), createPawn(PlayerColor.WHITE));
            addPiece(new Coordinate(i, 6), createPawn(PlayerColor.BLACK));
        }
    }

    private void addPiece(Coordinate coordinate, Piece piece){
        for (Position position : history.get(history.size()-1)) {
            if (position.getCoordinate().compareTo(coordinate) == 0) position.setPiece(piece);
        }
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
    public void revertMove(Move move) {
        history.remove(history.size()-1);
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
    public int getSize() {
        return size;
    }
}
