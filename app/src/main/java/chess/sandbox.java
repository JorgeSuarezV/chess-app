package main.java;

import piece.Color;
import piece.Piece;
import position.Coordinate;
import position.Position;

import static piece.PieceFactory.createRook;

public class sandbox {
    public static void main(String[] args) {
        Piece rook = createRook(Color.WHITE);
        Position position = new Position(new Coordinate(0,0), rook);
        Piece piece = position.setPiece(null);
        System.out.println(piece);

    }
}
