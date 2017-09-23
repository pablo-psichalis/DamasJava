package Token;

import Board.Board;
import Board.Move;
import Board.Square;

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
/*        if (this.isKing()) {

        }
*/
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

        return !squaresAtBorderForwardLeft.contains(enemyTokenPosition) &&
                !squaresAtBorderForwardLeft.contains(currentPos) &&
                board.isOccupiedByRed(enemyTokenPosition) &&
                !board.isOccupied(jumpDestination);
    }

    private boolean canMoveForwardRight(Board board, int currentPos) {

        int possibleDestination
                = (currentPos + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        return !squaresAtBorderForwardRight.contains(currentPos) &&
                !board.isOccupied(possibleDestination);
    }

    private boolean canCaptureTokenForwardRight(Board board, int currentPos) {

        int enemyTokenPosition
                = (currentPos + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;
        int jumpDestination =
                (currentPos + BLACK.getOffsetForwardRight() * 2) % Board.MAX_SQUARE_ID;

        return !squaresAtBorderForwardRight.contains(enemyTokenPosition) &&
                !squaresAtBorderForwardRight.contains(currentPos) &&
                board.isOccupiedByRed(enemyTokenPosition) &&
                !board.isOccupied(jumpDestination);
    }

    public ArrayList<Move> jumpPathUntil(int destination, ArrayList<Move> moveList, Board board) {

        ArrayList<Move> path = new ArrayList<>();
        ArrayList<Move> remainingMoves = (ArrayList<Move>) moveList.clone();

        int initialPos = this.getCurrentSquareIdentifier();
        int temp = destination;

        while ((temp != initialPos) && (remainingMoves.size() > 0)) {
            boolean found = false;
            for (int i = 0; i < remainingMoves.size() && !found; i++) {
                if (remainingMoves.get(i).getDestination() == temp) {
                    path.add(0, remainingMoves.get(i));
                    temp = remainingMoves.get(i).getOrigin();
                    remainingMoves.remove(i);
                    found = true;
                }
            }
        }

        System.out.println("JumpPath: " + path.toString()); // TODO: Quitar, solo es para testing
        return path;
    }

    public boolean canMove(Board board, int destination) {
        int curPos = this.getCurrentSquareIdentifier();
        if (!board.isOccupied(destination)) {
            return (
                    ((destination == (curPos + BLACK.getOffsetForwardRight()) % Board.MAX_SQUARE_ID) &&
                            !squaresAtBorderForwardRight.contains(destination)) ||
                            ((destination == (curPos + BLACK.getOffsetForwardLeft() % Board.MAX_SQUARE_ID) &&
                                    !squaresAtBorderForwardLeft.contains(destination)))
            );
        } else {
            return false;
        }

        //TODO: Añadir caso para piezas coronadas
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