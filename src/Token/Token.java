package Token;

import Board.Move;
import Board.Board;

import java.util.ArrayList;

public abstract class Token {

    private boolean isKing;
    private int currentSquareIdentifier;
    private Color color;

    Token(boolean isKing, int currentSquareIdentifier, Color color, int direction) {
        this.isKing = isKing;
        this.currentSquareIdentifier = currentSquareIdentifier;
        this.color = color;
    }

    /**
     * Returns true if the Token contained in the supplied square
     * from the given board has a colour different from
     * this Token's colour. False otherwise.
     */
    public abstract void calculateLegalMoves(int curSquare, Board board, ArrayList<Move> moveList);

    public boolean equals(Token t) {

        return this.isKing == t.isKing &&
                this.currentSquareIdentifier == t.currentSquareIdentifier &&
                this.color == t.color;
    }

    boolean isKing() {
        return this.isKing;
    }

    public int getCurrentSquareIdentifier() {
        return currentSquareIdentifier;
    }

    void setCurrentSquareIdentifier(int currentSquareIdentifier) {
        this.currentSquareIdentifier = currentSquareIdentifier;
    }


    public abstract void move(Board board, int destination, ArrayList<Move> moveList);
}
