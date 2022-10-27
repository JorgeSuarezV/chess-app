package chess.position;


import chess.position.Board;
import chess.position.CapablancaBoard;
import chess.position.ClassicBoard;

public class BoardFactory {


    public static Board createClassicBoard(){
        return new ClassicBoard().addDefaultBoardPieces();
    }

    public static Board createMusketeerBoard(){
        return new ClassicBoard().addMusketeers();
    }


    public static Board createCapablancaBoard(){
        return new CapablancaBoard().addDefaultBoardPieces();
    }

}
