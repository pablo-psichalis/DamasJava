package Token;

import Board.Board;
import Board.Move;

import java.util.List;

import static Token.Color.RED;

public class TokenRed extends Token {

    public TokenRed(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, RED, RED.getDirection());
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        // TODO: completar calculateLegalMoves
        return null;
    }

    @Override
    public boolean canCapture(Token t) {
        // TODO: completar canCapture
        return false;
    }


}
