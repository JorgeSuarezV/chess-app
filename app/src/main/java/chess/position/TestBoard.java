package chess.position;

import chess.piece.Piece;

public interface TestBoard extends Board {
    void addPiece(Coordinate coordinate, Piece piece);

    void removePiece(Coordinate coordinate);
}
