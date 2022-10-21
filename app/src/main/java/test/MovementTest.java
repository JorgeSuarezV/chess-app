package test;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static chess.piece.PieceFactory.*;

public class MovementTest {

    @Test
    public void rookMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,5)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,1)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,0)));
        validMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,6)));

        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,7)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(7,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,-5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(0,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-3,0)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(-1,5)));
        invalidMoves.add(new Move(new Coordinate(0,0), new Coordinate(1,5)));
        for (Move move : validMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(0,0), createRook(Color.WHITE));
            map.put(new Coordinate(0,6), createRook(Color.BLACK));
            map.put(new Coordinate(5,0), createRook(Color.BLACK));
            board.addPieces(map);
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createRook(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(0,0), createRook(Color.WHITE));
            map.put(new Coordinate(0,6), createRook(Color.BLACK));
            map.put(new Coordinate(5,0), createRook(Color.BLACK));
            board.addPieces(map);
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }

    @Test
    public void knightMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(5,4)));
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(4,1)));
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,2)));

        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(0,7)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,1)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(0,3)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,2)));


        for (Move move : validMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createKnight(Color.WHITE));
            map.put(new Coordinate(1,2), createKnight(Color.BLACK));
            board.addPieces(map);
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createKnight(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createKnight(Color.WHITE));
            map.put(new Coordinate(1,2), createRook(Color.WHITE));
            board.addPieces(map);
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }

    @Test
    public void kingMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(4,4)));
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(2,2)));
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,2)));


        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(0,7)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,1)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(0,3)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,2)));


        for (Move move : validMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createKing(Color.WHITE));
            map.put(new Coordinate(2,2), createKnight(Color.BLACK));
            board.addPieces(map);
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createKing(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createKing(Color.WHITE));
            board.addPieces(map);
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }

    @Test
    public void pawnMovementTest(){
        List<Move> validMoves = new ArrayList<>();
        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,4)));
        validMoves.add(new Move(new Coordinate(3,4), new Coordinate(3,5)));

        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,5)));
        validMoves.add(new Move(new Coordinate(3,5), new Coordinate(3,6)));

        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(4,4)));
        validMoves.add(new Move(new Coordinate(4,4), new Coordinate(4,5)));

        validMoves.add(new Move(new Coordinate(3,3), new Coordinate(2,4)));
        validMoves.add(new Move(new Coordinate(2,4), new Coordinate(2,5)));


        List<Move> invalidMoves = new ArrayList<>();
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,6)));
        invalidMoves.add(new Move(new Coordinate(3,6), new Coordinate(3,7)));

        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,6)));
        invalidMoves.add(new Move(new Coordinate(3,6), new Coordinate(4,6)));

        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(1,1)));
        invalidMoves.add(new Move(new Coordinate(3,3), new Coordinate(3,2)));


        for (Iterator<Move> it = validMoves.iterator(); it.hasNext(); ) {
            Move move = it.next();
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createPawn(Color.WHITE));
            map.put(new Coordinate(4,4), createKnight(Color.BLACK));
            map.put(new Coordinate(2,4), createKnight(Color.BLACK));
            board.addPieces(map);
            Assert.assertEquals("ok", board.makeMove(move));
            move = it.next();
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createPawn(piece.getColor())));
        }
        for (Iterator<Move> it = invalidMoves.iterator(); it.hasNext(); ) {
            Move move = it.next();
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            Map<Coordinate, Piece> map = new HashMap<>();
            map.put(new Coordinate(3,3), createPawn(Color.WHITE));
            map.put(new Coordinate(4,4), createKnight(Color.BLACK));
            map.put(new Coordinate(2,4), createKnight(Color.BLACK));
            board.addPieces(map);
            Assert.assertNotEquals("ok", board.makeMove(move));
            move = it.next();
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
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            board.addDefaultBoardPieces();
            Assert.assertEquals("ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createBishop(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
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
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            board.addDefaultBoardPieces();
            Assert.assertEquals(move.getTo().getX() +"" ,"ok", board.makeMove(move));
            Piece piece = board.getPosition(move.getTo()).getPiece();
            Assert.assertEquals(1, piece.compareTo(createQueen(piece.getColor())));
        }
        for (Move move : invalidMoves) {
            TestBoard board = new TestBoard(new ClassicValidator(new HashSet<>()));
            board.addDefaultBoardPieces();
            Assert.assertNotEquals("ok", board.makeMove(move));
        }
    }
}
