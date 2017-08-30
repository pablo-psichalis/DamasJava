package Token;

import Board.Board;
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
        // TODO: Completar
        return false;
    }

    public boolean canMoveForwardRight(Board board) {
        // TODO: Completar
        return false;
    }


    @Override
    public boolean canCapture(Token t) {
        // TODO: completar canCapture
        return false;
    }
}