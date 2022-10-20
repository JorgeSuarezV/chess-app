package test;

import main.java.piece.Piece;
import main.java.position.Board;
import main.java.position.Coordinate;
import main.java.position.DefaultBoard;
import main.java.position.Move;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static main.java.piece.PieceFactory.createRook;

public class MovementTest {

    @Test
    public void rookMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,1)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,5)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(7,0)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,0)));

        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,-5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-3,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-1,5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,5)));
        for (Move move : validMoves) {
            Board board = new DefaultBoard();
            board.addDefaultBoardPieces();
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createRook(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            Board board = new DefaultBoard();
            board.addDefaultBoardPieces();
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }
}
