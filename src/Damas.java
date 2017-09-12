import Board.*;
import Token.Token;

import java.util.ArrayList;
import java.util.Map;

public class Damas {

    public static void main(String[] args) {

        // Tests de calculateLegalMoves()

        // Regular board

        Board regular_board = new Board();
        printBoard(regular_board);

        testCalculateLegalMovesBlack(regular_board);


        // Custom board

        final int[] CUST_INITIAL_RED_POSITIONS_ID = {11, 3, 29, 23, 17, 27, 21, 15, 9, 19, 13, 7, 1};
        final int[] CUST_INITIAL_BLACK_POSITIONS_ID = {5, 31, 25, 30, 18, 12, 6, 0};
        final int[] CUST_INITIAL_EMPTY_POSITIONS_ID = {10, 4, 24, 2, 28, 22, 16, 26, 20, 14, 8};

        Board custom_board = new Board(
                CUST_INITIAL_RED_POSITIONS_ID, CUST_INITIAL_BLACK_POSITIONS_ID, CUST_INITIAL_EMPTY_POSITIONS_ID
        );

        printBoard(custom_board);

        testCalculateLegalMovesBlack(custom_board);

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


    private static void printBoard(Board board) {

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

}
