package Token;

import Board.Move;
import Board.Board;

import java.util.List;

import static Token.Color.BLACK;

public abstract class Token {

    private boolean isKing;
    private int currentSquareIdentifier;
    private Color color;
    private int direction;

    public Token(boolean isKing, int currentSquareIdentifier, Color color, int direction) {
        this.isKing = isKing;
        this.currentSquareIdentifier = currentSquareIdentifier;
        this.color = color;
    }

    /**
     * Returns true if the supplied Piece belongs to a Player other than this Piece's Player. false otherwise.
     */
    public abstract boolean canCapture(Token t);

    public abstract List<Move> calculateLegalMoves(Board board);


    public boolean equals(Token t) {

        return this.isKing == t.isKing &&
                this.currentSquareIdentifier == t.currentSquareIdentifier &&
                this.color == t.color &&
                this.direction == t.direction;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public int getCurrentSquareIdentifier() { return currentSquareIdentifier; }
}
