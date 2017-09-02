package Token;

import Board.Board;
import Board.Square;
import Board.Move;

import java.util.ArrayList;
import java.util.List;

import static Token.Color.BLACK;

public class TokenBlack extends Token {

    public TokenBlack(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, BLACK, BLACK.getDirection());
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        // TODO: completar calculateLegalMoves

/*      int curSquareId = this.getCurrentSquareIdentifier();
        List<Move> candidates = new ArrayList<Move>();

        if (!isKing()) {

        } else {

        }
*/
        return null;
    }

    public boolean canMoveForwardLeft(Board board) {

        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;

        if (!board.isOccupied(possibleDestination)) {
            return true;
        } else if (board.isOccupiedByBlack(possibleDestination)) {
            return false;
        } else {
            assert board.isOccupiedByRed(possibleDestination);
            return (
                    !board.isOccupied(
                            (possibleDestination + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID
                    )
            );
        }
    }

    public boolean canMoveForwardRight(Board board) {
        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        if (!board.isOccupied(possibleDestination)) {
            return true;
        } else if (board.isOccupiedByBlack(possibleDestination)) {
            return false;
        } else {
            assert board.isOccupiedByRed(possibleDestination);
            return (
                    !board.isOccupied(
                            (possibleDestination + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID
                    )
            );
        }
    }

    public boolean canCapture(int tokenSquareId, Board board) {
        if(board.getOccupiedByRed().containsKey(tokenSquareId)){
            return true;
        } else {
            return false;
        }
    }
}