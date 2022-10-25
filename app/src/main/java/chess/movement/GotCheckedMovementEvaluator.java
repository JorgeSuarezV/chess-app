package chess.movement;

import chess.exception.MovementException;
import chess.exception.OutOfBoundsException;
import chess.exception.SelfCheckException;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.position.Board;
import chess.position.Coordinate;
import chess.position.Move;
import chess.position.Position;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;
import java.util.Set;

public class GotCheckedMovementEvaluator implements MovementEvaluator{

    @Override
    public Set<Move> isValidMove(Board board, Move move, Set<Move> moves) throws SelfCheckException, MovementException, OutOfBoundsException {
        Board board1 = board.clone();
        board1.movePiece(moves);
        Position position = board1.getPosition(move.getTo());
        if (position == null) throw new OutOfBoundsException();
        Piece piece = position.getPiece();
        if (piece == null) throw new MovementException("This piece is not yours");
        PlayerColor playerColor = piece.getPlayerColor();
        PlayerColor oponentColor = playerColor == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        List<Position> actualPositions = board1.getActualPositions();
        Coordinate coordinate = new Coordinate(0, 0);

        for (Position actualPosition : actualPositions) { // searchForKing
            if (actualPosition.getPiece() != null && actualPosition.getPiece().getPlayerColor() == playerColor && actualPosition.getPiece().getType() == PieceType.KING) {
                coordinate = actualPosition.getCoordinate();
                break;
            }
        }

        for (Position actualPosition : actualPositions) { // check all enemy Pieces
            if (actualPosition.getPiece() != null && actualPosition.getPiece().getPlayerColor() == oponentColor) {
                for (MovementEvaluator summativeMovementEvaluator : actualPosition.getPiece().getMovementEvaluators()) { // check if threat
                    try {
                        if (summativeMovementEvaluator.isThreatening(board1, new Move(actualPosition.getCoordinate(), coordinate)))
                            throw new SelfCheckException();
                    }catch (Exception e){
                        if (e.getClass() == SelfCheckException.class)
                            throw new SelfCheckException();
                    }
                }
            }
        }
        moves.add(move);
        return moves;
    }

    @Override
    public boolean isThreatening(Board board, Move move) {
        return false;
    }

    @Override
    public MovementEvaluator addMovementEvaluator(MovementEvaluator movementEvaluator) {
        return null;
    }
}
