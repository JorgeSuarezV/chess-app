package test;


import chess.position.*;
import edu.austral.dissis.chess.gui.PlayerColor;
import org.junit.Assert;
import org.junit.Test;

import static chess.piece.PieceFactory.createRook;


public class BoardTest {

    @Test
    public void emptyBoard(){
        Board board = new ClassicBoard(8);
        for (Position actualPosition : board.getActualPositions()) {
            Assert.assertNull(actualPosition.getPiece());
        }
    }

    @Test
    public void testDefaultBoard(){
        Board board = new ClassicBoard(8);
        board.addDefaultBoardPieces();
        int rookCount = 0;
        for (Position actualPosition : board.getActualPositions()) {
            if (actualPosition.getPiece() == null) continue;
            if (actualPosition.getCoordinate().compareTo(new Coordinate(0,0)) == 0 ||
                    actualPosition.getCoordinate().compareTo(new Coordinate(7,0)) == 0){
                Assert.assertEquals(1, actualPosition.getPiece().compareTo(createRook(PlayerColor.WHITE)));
                rookCount++;
                continue;
            }
            if (actualPosition.getCoordinate().compareTo(new Coordinate(0,7)) == 0 ||
                    actualPosition.getCoordinate().compareTo(new Coordinate(7,7)) == 0 ){
                Assert.assertEquals(1, actualPosition.getPiece().compareTo(createRook(PlayerColor.BLACK)));
                rookCount++;
                continue;
            }
        }
        Assert.assertEquals(4, rookCount);
    }
}
