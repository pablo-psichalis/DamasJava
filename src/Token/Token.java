package Token;

import Board.Move;
import Board.Board;

import java.util.List;

public abstract class Token {

    private boolean isKing;

    public Token(boolean isKing) {
        this.isKing = false;
    }

    // Returns true if the supplied Piece belongs to a Player other than this Piece's Player. false otherwise.
    public abstract boolean canCapture(Token t);

    // Returns the legal moves
    public abstract List<Move> calculateLegalMoves(Board board);

    public boolean isKing() {
        return this.isKing;
    }

}
