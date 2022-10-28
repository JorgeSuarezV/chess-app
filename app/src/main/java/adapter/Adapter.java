package adapter;

import chess.piece.PieceType;
import chess.position.Coordinate;
import chess.position.Position;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter {

    public static List<ChessPiece> getChessPieceList(
        List<Position> actualState
    ) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (Position position : actualState) {
            if (position.getPiece() == null) continue;
            chessPieces.add(
                new ChessPiece(
                    position.getPiece().getName(),
                    position.getPiece().getPlayerColor(),
                    new edu.austral.dissis.chess.gui.Position(
                        position.getY() + 1,
                        position.getX() + 1
                    ),
                    pieceTypeAdapter(position.getPiece().getType())
                )
            );
        }
        return chessPieces;
    }

    public static String pieceTypeAdapter(PieceType pieceType) {
        if (
            pieceType == PieceType.MOSKETEER
        ) return "chancellor"; else return pieceType.name().toLowerCase();
    }

    public static chess.position.Move castGUIMoveToMove(Move move) {
        return new chess.position.Move(
            new Coordinate(
                move.getFrom().getColumn() - 1,
                move.getFrom().getRow() - 1
            ),
            new Coordinate(
                move.getTo().getColumn() - 1,
                move.getTo().getRow() - 1
            )
        );
    }
}
