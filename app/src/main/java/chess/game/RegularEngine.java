package chess.game;

import chess.piece.PieceType;
import chess.position.*;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static chess.Adapter.castGUIMoveToMove;
import static chess.Adapter.getChessPieceList;

public class RegularEngine implements GameEngine{

    Player player1;
    Player player2;

    PlayerColor currentPlayerColor = PlayerColor.WHITE;
    Board board;
    List<Validator> validators = new ArrayList<>();


    public RegularEngine() {
        this.player1 = new Player(PlayerColor.WHITE);
        this.player2 = new Player(PlayerColor.BLACK);
        this.board = new ClassicBoard(8);
        validators.add(new ClassicMoveValidator());
        validators.add(new PromotionValidator(PieceType.PAWN));
        validators.add(new WinValidator());
     }

    @NotNull
    @Override
    public InitialState init() {
        board.addDefaultBoardPieces();
        return new InitialState(new BoardSize(8,8), getChessPieceList(board.getActualPositions()), PlayerColor.WHITE);
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
}


