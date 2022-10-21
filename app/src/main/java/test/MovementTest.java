package test;

import chess.piece.Piece;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.ClassicBoard;
import chess.position.Move;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static chess.piece.PieceFactory.*;

public class MovementTest {

    @Test
    public void rookMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,1)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,5)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,0)));

        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(7,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,-5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-3,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-1,5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,5)));
        for (Move move : validMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createRook(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }

    @Test
    public void bishopMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(2,0), new Coordinate(5,3)));
        validMoves.add(new Move(new Coordinate(5,7), new Coordinate(6,6)));
        validMoves.add(new Move(new Coordinate(5,0), new Coordinate(0,5)));
        validMoves.add(new Move(new Coordinate(2,7), new Coordinate(4,5)));


        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(2,0)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(1,0)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(0,1)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(7,0)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(0,-5)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(-3,0)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(-1,5)));
        invalidMoves.add(new Move(new Coordinate(2,0), new Coordinate(1,5)));
        for (Move move : validMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createBishop(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }
    @Test
    public void queenMovementTest() {
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(3, 0), new Coordinate(5, 2)));
        validMoves.add(new Move(new Coordinate(3, 0), new Coordinate(7, 4)));
        validMoves.add(new Move(new Coordinate(3, 0), new Coordinate(3, 5)));


        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(1, 0)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(2, 0)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(2, 5)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(0, 1)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(1, 1)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(1, 4)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(0, -5)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(-3, 0)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(-1, 5)));
        invalidMoves.add(new Move(new Coordinate(3, 0), new Coordinate(1, 5)));
        for (Move move : validMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertEquals(move.getTo().getX() +"" ,"ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createQueen(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            Board board = new ClassicBoard();
            board.addDefaultBoardPieces();
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }
}
