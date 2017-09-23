import Board.*;
import Token.Token;
import Token.Color;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Damas {

    private Board board;
    private Turn turn;

    public static void main(String[] args) {

        Damas damas = new Damas();

        // Tests de calculateLegalMoves()

        // Regular board
        damas.printBoard(damas.board);
/*        testCalculateLegalMovesBlack(damas.board);

        // Custom board

        final int[] CUST_INITIAL_RED_POSITIONS_ID = {11, 3, 29, 23, 17, 27, 21, 15, 9, 19, 13, 7, 1};
        final int[] CUST_INITIAL_BLACK_POSITIONS_ID = {5, 31, 25, 30, 18, 12, 6, 0};
        final int[] CUST_INITIAL_EMPTY_POSITIONS_ID = {10, 4, 24, 2, 28, 22, 16, 26, 20, 14, 8};

        Board custom_board = new Board(
                CUST_INITIAL_RED_POSITIONS_ID, CUST_INITIAL_BLACK_POSITIONS_ID, CUST_INITIAL_EMPTY_POSITIONS_ID
        );

        damas.printBoard(custom_board);
        testCalculateLegalMovesBlack(custom_board);*/


        damas.play();

    }

    private Damas() {
        turn = new Turn();
        board = new Board();
    }

    private static final int[] ALL_SQUARE_IDS =
            {
                    11, 5, 31, 25,
                    10, 4, 30, 24,
                    3, 29, 23, 17,
                    2, 28, 22, 16,
                    27, 21, 15, 9,
                    26, 20, 14, 8,
                    19, 13, 7, 1,
                    18, 12, 6, 0
            };

    private void printBoard(Board board) {

        int i = 0;

        System.out.println("  A B C D E F G H");
        for (int row = 0; row < Board.NUM_ROWS; row++) {
            System.out.print(row + 1);
            if (row % 2 == 0) {
                for (int col = 0; col < Board.NUM_COLUMNS; col += 2, i++) {
                    System.out.print("| ");
                    printRetrievedToken(ALL_SQUARE_IDS[i], board);
                }
            } else {
                for (int col = 0; col < Board.NUM_COLUMNS; col += 2, i++) {
                    printRetrievedToken(ALL_SQUARE_IDS[i], board);
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }

    }

    private static void printRetrievedToken(int id, Board board) {
        if (board.getOccupiedByRed().containsKey(id)) {
            System.out.print("|□");
        } else if (board.getOccupiedByBlack().containsKey(id)) {
            System.out.print("|■");
        } else {
            System.out.print("| ");
        }
    }

    private void play() {
        do {
            System.out.println(turn.toString() + "'s turn");

            boolean endTurn = false;
            ArrayList<Move> moveList = new ArrayList<>();

            do {
                // TODO: Refactorizar

                System.out.print("Choose a " + turn.toString() + " token: ");
                Scanner sc = new Scanner(System.in);

                if (sc.hasNext("[A-H][1-8]")) {
                    String inputCurPos = sc.nextLine();
                    sc.reset();

                    Integer tokenPos = BoardUtils.translateToSquareId(inputCurPos);

                    if (tokenPos != null) {
                        if ((this.board.isOccupiedByBlack(tokenPos) && this.turn.getColor() == Color.BLACK) ||
                                (this.board.isOccupiedByRed(tokenPos) && this.turn.getColor() == Color.RED)) {

                            this.board.getToken(tokenPos).calculateLegalMoves(tokenPos, board, moveList);

                            if (!moveList.isEmpty()) {
                                System.out.print("Choose its destination: ");
                                if (sc.hasNext("[A-H][1-8]")) {
                                    String inputDest = sc.nextLine();
                                    sc.reset();

                                    Integer tokenDestination = BoardUtils.translateToSquareId(inputDest);

                                    boolean moveValidated = false;
                                    if (tokenDestination != null) {
                                        for (int i = 0; i < moveList.size() && !moveValidated; i++) {
                                            if (moveList.get(i).getDestination() == tokenDestination) {
                                                System.out.println("Valid move!");
                                                moveValidated = true;
                                            }
                                        }

                                        if (moveValidated) {
                                            this.board.getToken(tokenPos).move(board, tokenDestination, moveList);
                                            this.printBoard(board);
                                            endTurn = true;
                                        } else {
                                            System.out.println("Illegal move!");
                                            System.out.println("Legal moves: " + moveList.toString());
                                        }
                                    } else {
                                        System.out.println("Invalid coordinates: Can't move to " + inputDest + "! Try again.");
                                        System.out.println("Legal moves: " + moveList.toString());
                                    }
                                } else {
                                    System.out.println("Invalid format. Use [Column][Row] (e.g. B1). Try again.");
                                }

                            } else {
                                System.out.println("Error: " + inputCurPos + " can't perform any legal movement. Try again.");
                            }

                        } else {
                            System.out.println("Invalid coordinates: " + inputCurPos + " does not contain a " + turn.toString() + " token. Try again.");
                        }

                    } else {
                        System.out.println("Invalid coordinates: " + inputCurPos + " is a non-playable square (and/or out of range). Try again.");
                    }

                } else {
                    System.out.println("Invalid format. Use [Column][Row] (e.g. B1). Try again.");
                }

                System.out.println("");
            } while (!endTurn);

            this.turn.switchTurn();

        } while (true); // TODO: Ajustar condición
    }

    private static void testCalculateLegalMovesBlack(Board board) {
        ArrayList<Move> moves;
        Map<Integer, Square> black_tokens = board.getOccupiedByBlack();

        System.out.println();

        for (Map.Entry<Integer, Square> entry : black_tokens.entrySet()) {
            Token cito = entry.getValue().getToken();
            moves = new ArrayList<Move>();
            cito.calculateLegalMoves(cito.getCurrentSquareIdentifier(), board, moves);

            if (moves.isEmpty()) {
                System.out.println(entry.getKey() + ": no legal moves found");
            } else {
                System.out.println(entry.getKey() + ":");
                for (Move m : moves) {
                    System.out.println("  found " + m.toString());
                }
            }

        }
    }

}
