package Token;

import Board.Board;
import Board.Move;
import Board.Square;

import java.util.ArrayList;

import static Token.Color.RED;

public class TokenRed extends Token {

    public TokenRed(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, RED);
    }

    public boolean canCapture(int squareIdentifier, Board board) {
        return false;
    }

    @Override
    public void move(Board board, int destination, ArrayList<Move> moveList) {

        int initialPos = this.getCurrentSquareIdentifier();

        if (canMove(board, destination)) {

            board.getOccupiedByRed().remove(initialPos);
            board.getEmptySquares().put(initialPos, new Square(initialPos, null));

            board.getOccupiedByRed().put(destination, new Square(destination, this));
            board.getEmptySquares().remove(destination);

            this.setCurrentSquareIdentifier(destination);

            System.out.println("Board updated!");

        } else if (!jumpPathUntil(destination, moveList, board).isEmpty()) {
            // TODO: Caso de las capturas; updatear tablero para saltos de varias fichas
        }
    }
}
