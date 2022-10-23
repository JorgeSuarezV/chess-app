package chess;

import chess.position.Coordinate;
import chess.position.Position;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;

import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public static List<ChessPiece> getChessPieceList(List<Position> actualState){
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (Position position : actualState) {
            if (position.getPiece() == null) continue;
            chessPieces.add(new ChessPiece(position.getPiece().getName(),
                    position.getPiece().getPlayerColor(),
                    new edu.austral.dissis.chess.gui.Position(position.getY() + 1,
                    position.getX() + 1),
                    position.getPiece().getType().name().toLowerCase()));
        }
        return chessPieces;
    }

    public static chess.position.Move castGUIMoveToMove(Move move){
        return new chess.position.Move(
                new Coordinate(move.getFrom().getColumn() - 1, move.getFrom().getRow() - 1),
                new Coordinate(move.getTo().getColumn() - 1, move.getTo().getRow()-1));
    }
}
