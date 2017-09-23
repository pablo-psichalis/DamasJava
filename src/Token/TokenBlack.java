package Token;

import Board.Board;
import Board.Move;
import Board.Square;

import java.util.*;

import static Token.Color.BLACK;

public class TokenBlack extends Token {

    public TokenBlack(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, BLACK);
    }

    /**
     *  If the token can somehow move to the destination in the given board,
     *  performs the move updating the board accordingly.
     *  This includes simple movements and captures.
     */
    @Override
    public void move(Board board, int destination, ArrayList<Move> moveList) {

        int initialPos = this.getCurrentSquareIdentifier();

        if (canMove(board, destination)) {

            board.getOccupiedByBlack().remove(initialPos);
            board.getEmptySquares().put(initialPos, new Square(initialPos, null));

            board.getOccupiedByBlack().put(destination, new Square(destination, this));
            board.getEmptySquares().remove(destination);

            this.setCurrentSquareIdentifier(destination);

            System.out.println("Board updated!");

        } else if (!jumpPathUntil(destination, moveList, board).isEmpty()) {
            // TODO: Caso de las capturas; updatear tablero para saltos de varias fichas
        }
    }
}