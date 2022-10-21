package test;



import chess.piece.Color;
import chess.position.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

import static chess.piece.PieceFactory.createRook;


public class BoardTest {

    @Test
    public void emptyBoard(){
        Board board = new ClassicBoard(new ClassicValidator(new HashSet<>()));
        for (Position actualPosition : board.getActualPositions()) {
            Assert.assertNull(actualPosition.getPiece());
        }
    }

    @Test
    public void testDefaultBoard(){
        Board board = new ClassicBoard(new ClassicValidator(new HashSet<>()));
        board.addDefaultBoardPieces();
        int rookCount = 0;
        for (Position actualPosition : board.getActualPositions()) {
            if (actualPosition.getPiece() == null) continue;
            if (actualPosition.getCoordinate().compareTo(new Coordinate(0,0)) == 0 ||
                    actualPosition.getCoordinate().compareTo(new Coordinate(7,0)) == 0){
                Assert.assertEquals(1, actualPosition.getPiece().compareTo(createRook(Color.WHITE)));
                rookCount++;
                continue;
            }
            if (actualPosition.getCoordinate().compareTo(new Coordinate(0,7)) == 0 ||
                    actualPosition.getCoordinate().compareTo(new Coordinate(7,7)) == 0 ){
                Assert.assertEquals(1, actualPosition.getPiece().compareTo(createRook(Color.BLACK)));
                rookCount++;
                continue;
            }
        }
        Assert.assertEquals(4, rookCount);
    }
}
