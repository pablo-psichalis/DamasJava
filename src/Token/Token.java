package Token;

import Board.Move;
import Board.Board;

import java.util.ArrayList;

import static Token.Color.BLACK;

public abstract class Token {

    private boolean isKing;
    private int currentSquareIdentifier;
    private Color color;

    Token(boolean isKing, int currentSquareIdentifier, Color color) {
        this.isKing = isKing;
        this.currentSquareIdentifier = currentSquareIdentifier;
        this.color = color;
    }

    private boolean canCaptureTokenForwardLeft(Board board, int currentPos) {

        int enemyTokenPosition =
                (currentPos + color.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;
        int jumpDestination =
                (currentPos + color.getOffsetForwardLeft() * 2) % Board.MAX_SQUARE_ID;

        if (this.getColor().isSquareAtBorderForwardLeft(enemyTokenPosition) ||
                this.getColor().isSquareAtBorderForwardLeft(currentPos) ||
                board.isOccupied(jumpDestination)) {
            return false;
        }

        if (color == Color.BLACK) {
            return board.isOccupiedByRed(enemyTokenPosition);
        } else {
            return board.isOccupiedByBlack(enemyTokenPosition);
        }
    }

    private boolean canCaptureTokenForwardRight(Board board, int currentPos) {

        int enemyTokenPosition
                = (currentPos + color.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;
        int jumpDestination =
                (currentPos + color.getOffsetForwardRight() * 2) % Board.MAX_SQUARE_ID;

        if (this.getColor().isSquareAtBorderForwardRight(enemyTokenPosition) ||
                this.getColor().isSquareAtBorderForwardRight(currentPos) ||
                board.isOccupied(jumpDestination)) {
            return false;
        }

        if (color == Color.BLACK) {
            return board.isOccupiedByRed(enemyTokenPosition);
        } else {
            return board.isOccupiedByBlack(enemyTokenPosition);
        }
    }

    private boolean canMoveForwardLeft(Board board, int currentPos) {

        int possibleDestination
                = (currentPos + color.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID;

        return !this.getColor().isSquareAtBorderForwardLeft(currentPos) &&
                (!board.isOccupied(possibleDestination));
    }

    private boolean canMoveForwardRight(Board board, int currentPos) {

        int possibleDestination
                = (currentPos + color.getOffsetForwardRight()) % Board.MAX_SQUARE_ID;

        return !this.getColor().isSquareAtBorderForwardRight(currentPos) &&
                !board.isOccupied(possibleDestination);
    }

    public void calculateLegalMoves(int curSquare, Board board, ArrayList<Move> moveList) {

        if (canMoveForwardLeft(board, curSquare)) {
            moveList.add(
                    new Move(curSquare, (curSquare + this.color.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID)
            );
        }

        if (canMoveForwardRight(board, curSquare)) {
            moveList.add(
                    new Move(curSquare, (curSquare + this.color.getOffsetForwardRight()) % Board.MAX_SQUARE_ID)
            );
        }

        if (canCaptureTokenForwardLeft(board, curSquare)) {

            int destination = (curSquare + (this.color.getOffsetForwardLeft() * 2)) % Board.MAX_SQUARE_ID;
            moveList.add(new Move(curSquare, destination));

            calculateLegalMoves(destination, board, moveList);
        }

        if (canCaptureTokenForwardRight(board, curSquare)) {

            int destination = (curSquare + (this.color.getOffsetForwardRight() * 2)) % Board.MAX_SQUARE_ID;
            moveList.add(new Move(curSquare, destination));
            calculateLegalMoves(destination, board, moveList);
        }

        // TODO: Añadir caso para piezas coronadas
/*        if (this.isKing()) {

        }
*/
    }

    public abstract void move(Board board, int destination, ArrayList<Move> moveList);

    protected boolean canMove(Board board, int destination) {
        int curPos = this.getCurrentSquareIdentifier();
        return !board.isOccupied(destination) &&
                (((destination == (curPos + color.getOffsetForwardRight()) % Board.MAX_SQUARE_ID) &&
                        !this.color.isSquareAtBorderForwardRight(curPos) ||
                ((destination == ((curPos + color.getOffsetForwardLeft()) % Board.MAX_SQUARE_ID) &&
                        !this.color.isSquareAtBorderForwardLeft(curPos)))));

        //TODO: Añadir caso para piezas coronadas
    }

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


    protected ArrayList<Move> jumpPathUntil(int destination, ArrayList<Move> moveList, Board board) {

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

    public Color getColor() { return color; }
}
