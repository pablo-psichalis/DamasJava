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
    public void calculateLegalMoves(int curSquare, Board board, ArrayList<Move> moveList) {

        if (canMoveForwardLeft(board)) {
            moveList.add(
                    new Move(curSquare, curSquare + BLACK.getOffsetForwardLeft() % Board.MAX_SQUARE_ID)
            );
        }

        if (canMoveForwardRight(board)) {
            moveList.add(
                    new Move(curSquare, curSquare + BLACK.getOffsetForwardRight() % Board.MAX_SQUARE_ID)
            );
        }

        if (canCaptureTokenForwardLeft(board)) {

            int destination = curSquare + ((BLACK.getOffsetForwardLeft() * 2) % Board.MAX_SQUARE_ID);
            moveList.add(new Move(curSquare, destination));

            calculateLegalMoves(destination, board, moveList);
        }

        if (canCaptureTokenForwardRight(board)) {

            int destination = curSquare + ((BLACK.getOffsetForwardRight() * 2) % Board.MAX_SQUARE_ID);
            moveList.add(new Move(curSquare, destination));

            calculateLegalMoves(destination, board, moveList);
        }

        // TODO: Testar y añadir caso para piezas coronadas

    }


    // TODO: Comprobar que el movimiento no se sale del tablero! (Ej: Mover del 01 al 02 no es válido!)
    public boolean canMoveForwardLeft(Board board) {

        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;

        return (!board.isOccupied(possibleDestination));
    }

    public boolean canCaptureTokenForwardLeft(Board board) {

        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;

        assert board.isOccupiedByRed(possibleDestination);
        return (
                !board.isOccupied(
                        (possibleDestination + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID
                )
        );
    }

    public boolean canMoveForwardRight(Board board) {

        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        return (!board.isOccupied(possibleDestination));
    }

    public boolean canCaptureTokenForwardRight(Board board) {

        int possibleDestination
                = (this.getCurrentSquareIdentifier() + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        return (

                board.isOccupiedByRed(possibleDestination) &&
                !board.isOccupied(
                        (possibleDestination + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID
                )
        );
    }

    public boolean canCapture(int tokenSquareId, Board board) {
        return (board.getOccupiedByRed().containsKey(tokenSquareId));
    }
}