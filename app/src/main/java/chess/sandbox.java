package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Coordinate;
import chess.position.Position;

import static chess.piece.PieceFactory.createRook;

public class sandbox {
    public static void main(String[] args) {
        Piece rook = createRook(Color.WHITE);
        Position position = new Position(new Coordinate(0,0), rook);
        Piece piece = position.setPiece(null);
        System.out.println(piece);

    }
}
