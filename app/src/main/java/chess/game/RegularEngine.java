package chess.game;

import chess.piece.PieceType;
import chess.position.*;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static adapter.Adapter.castGUIMoveToMove;
import static adapter.Adapter.getChessPieceList;
import static chess.position.BoardFactory.*;

public class RegularEngine implements GameEngine{

    private PlayerColor currentPlayerColor = PlayerColor.WHITE;
    private Board board;
    private final List<Validator> validators = new ArrayList<>();


    public RegularEngine() {
        validators.add(new ClassicMoveValidator());
        validators.add(new PromotionValidator(PieceType.PAWN));
        validators.add(new WinValidator());
     }

    @NotNull
    @Override
    public InitialState init() {
        this.board = chooseGameMode();
        return new InitialState(new BoardSize(board.getWidth(), board.getHeight()), getChessPieceList(board.getActualPositions()), PlayerColor.WHITE);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        chess.position.Move move1 = castGUIMoveToMove(move);
        try {
            MoveResult moveResult = new InvalidMove("something went wrong");
            for (Validator validator : validators) {
                MoveResult moveResult1 = validator.checkMove(board, move1, currentPlayerColor);
                if (moveResult1 != null) moveResult = moveResult1;
            }

        currentPlayerColor = currentPlayerColor == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        return moveResult;
        }catch (Exception e) {
            return new InvalidMove(e.getMessage());
        }
    }
    private Board chooseGameMode(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter game mode: \n 1 for Classic \n 2 for Capablanca \n 3 for MusketeerBoard");
        int gameMode = myObj.nextInt();
        if (gameMode == 1) return createClassicBoard();
        if (gameMode == 2) return createCapablancaBoard();
        if (gameMode == 3) return createMusketeerBoard();
        else return createClassicBoard();

    }
}


