package Token;

import Board.Board;
import Board.Move;

import java.util.ArrayList;

import static Token.Color.RED;

public class TokenRed extends Token {

    public TokenRed(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, RED, RED.getDirection());
    }

    public boolean canCapture(int squareIdentifier, Board board) {
        return false;
    }

    @Override
    public boolean canCapture(Board board, int squareIdentifier) {
        return false;
    }

    @Override
    public void calculateLegalMoves(int curSquare, Board board, ArrayList<Move> moveList) {

    }

    @Override
    public void move(Board board, int destination) {

    }

}
