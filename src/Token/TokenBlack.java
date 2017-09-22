package Token;

import Board.Board;
import Board.Move;

import java.util.*;

import static Token.Color.BLACK;

public class TokenBlack extends Token {

    private static final Set<Integer> squaresAtBorderForwardLeft =
            new HashSet<>(Arrays.asList(18, 26, 2, 10, 11, 5, 31, 25));

    private static final Set<Integer> squaresAtBorderForwardRight =
            new HashSet<>(Arrays.asList(1, 9, 17, 25, 31, 5, 11));

    public TokenBlack(boolean isKing, int currentSquareIdentifier) {
        super(isKing, currentSquareIdentifier, BLACK, BLACK.getDirection());
    }


    @Override
    public void calculateLegalMoves(int curSquare, Board board, ArrayList<Move> moveList) {

        if (canMoveForwardLeft(board, curSquare)) {
            moveList.add(
                    new Move(curSquare, (curSquare + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID)
            );
        }

        if (canMoveForwardRight(board, curSquare)) {
            moveList.add(
                    new Move(curSquare, (curSquare + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID)
            );
        }

        if (canCaptureTokenForwardLeft(board, curSquare)) {

            int destination = (curSquare + (BLACK.getOffsetForwardLeft() * 2)) % Board.MAX_SQUARE_ID;
            moveList.add(new Move(curSquare, destination));

            calculateLegalMoves(destination, board, moveList);
        }

        if (canCaptureTokenForwardRight(board, curSquare)) {

            int destination = (curSquare + (BLACK.getOffsetForwardRight() * 2)) % Board.MAX_SQUARE_ID;
            moveList.add(new Move(curSquare, destination));
            calculateLegalMoves(destination, board, moveList);
        }

        // TODO: Añadir caso para piezas coronadas
        if(this.isKing()){

        }
    }

    private boolean canMoveForwardLeft(Board board, int currentPos) {

        int possibleDestination
                = (currentPos + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;

        return !squaresAtBorderForwardLeft.contains(currentPos) &&
                (!board.isOccupied(possibleDestination));
    }

    private boolean canCaptureTokenForwardLeft(Board board, int currentPos) {

        int enemyTokenPosition =
                (currentPos + BLACK.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;
        int jumpDestination =
                (currentPos + BLACK.getOffsetForwardLeft() * 2) % Board.MAX_SQUARE_ID;

        return  !squaresAtBorderForwardLeft.contains(enemyTokenPosition) &&
                !squaresAtBorderForwardLeft.contains(currentPos) &&
                board.isOccupiedByRed(enemyTokenPosition) &&
                !board.isOccupied(jumpDestination);
    }

    private boolean canMoveForwardRight(Board board, int currentPos) {

        int possibleDestination
                = (currentPos + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        return  !squaresAtBorderForwardRight.contains(currentPos) &&
                !board.isOccupied(possibleDestination);
    }

    private boolean canCaptureTokenForwardRight(Board board, int currentPos) {

        int enemyTokenPosition
                = (currentPos + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;
        int jumpDestination =
                (currentPos + BLACK.getOffsetForwardRight() * 2) % Board.MAX_SQUARE_ID;

        return  !squaresAtBorderForwardRight.contains(enemyTokenPosition) &&
                !squaresAtBorderForwardRight.contains(currentPos) &&
                board.isOccupiedByRed(enemyTokenPosition) &&
                !board.isOccupied(jumpDestination);
    }

    public boolean canCapture(int tokenSquareId, Board board) {
        return (board.getOccupiedByRed().containsKey(tokenSquareId));
    }

    @Override
    public void mover(int posDestino) {
        // eliminar token de la pos inicial
        // comer?
        //
    }
}