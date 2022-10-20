package test;

import main.java.piece.Color;
import main.java.position.Board;
import main.java.position.Coordinate;
import main.java.position.DefaultBoard;
import main.java.position.Position;
import org.junit.Assert;
import org.junit.Test;

import static main.java.piece.PieceFactory.createRook;

public class BoardTest {

    @Test
    public void emptyBoard(){
        Board board = new DefaultBoard();
        for (Position actualPosition : board.getActualPositions()) {
            Assert.assertNull(actualPosition.getPiece());
        }
    }

    @Test
    public void testDefaultBoard(){
        Board board = new DefaultBoard();
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
