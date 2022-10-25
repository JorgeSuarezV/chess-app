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

public class CapablancaBoard implements Board {

    private final List<List<Position>> history;

    final int size = 10;

    public CapablancaBoard(){
        List<List<Position>> history1 = new ArrayList<>();
        history1.add(generatePositions());
        this.history = history1;
    }

    private CapablancaBoard(List<List<Position>> history) {
        this.history = history;
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

    private List<Position> generatePositions() {
        List<Position> positionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                positionList.add(new Position(i,j));
            }
        }
        return positionList;
    }

    @Override
    public Board clone() {
        return new CapablancaBoard(cloneBoard(history));
    }

    @Override
    public void addDefaultBoardPieces() {
        addPiece(new Coordinate(0,0), createRook(PlayerColor.WHITE));
        addPiece(new Coordinate(9,0), createRook(PlayerColor.WHITE));
        addPiece(new Coordinate(0,9), createRook(PlayerColor.BLACK));
        addPiece(new Coordinate(9,9), createRook(PlayerColor.BLACK));

        addPiece(new Coordinate(1,0), createKnight(PlayerColor.WHITE));
        addPiece(new Coordinate(8,0), createKnight(PlayerColor.WHITE));
        addPiece(new Coordinate(1,9), createKnight(PlayerColor.BLACK));
        addPiece(new Coordinate(8,9), createKnight(PlayerColor.BLACK));

        addPiece(new Coordinate(2,0), createArchbishop(PlayerColor.WHITE));
        addPiece(new Coordinate(2,9), createArchbishop(PlayerColor.BLACK));

        addPiece(new Coordinate(7,0), createChancellor(PlayerColor.WHITE));
        addPiece(new Coordinate(7,9), createChancellor(PlayerColor.BLACK));

        addPiece(new Coordinate(3,0), createBishop(PlayerColor.WHITE));
        addPiece(new Coordinate(6,0), createBishop(PlayerColor.WHITE));
        addPiece(new Coordinate(3,9), createBishop(PlayerColor.BLACK));
        addPiece(new Coordinate(6,9), createBishop(PlayerColor.BLACK));

        addPiece(new Coordinate(4,0), createQueen(PlayerColor.WHITE));
        addPiece(new Coordinate(4,9), createQueen(PlayerColor.BLACK));

        addPiece(new Coordinate(5,0), createKing(PlayerColor.WHITE, getWidth()));
        addPiece(new Coordinate(5,9), createKing(PlayerColor.BLACK, getWidth()));

        for (int i = 0; i < getWidth(); i++) {
            addPiece(new Coordinate(i,1), createPawn(PlayerColor.WHITE));
            addPiece(new Coordinate(i,8), createPawn(PlayerColor.BLACK));
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
}
