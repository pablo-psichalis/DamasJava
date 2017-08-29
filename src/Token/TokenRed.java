package Token;

import Board.Board;
import Board.Move;

import java.util.List;

public class TokenRed extends Token {

    public TokenRed(boolean isKing) {
        super(isKing);
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
