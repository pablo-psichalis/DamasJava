package Board;

import Token.*;

import java.util.ArrayList;
import java.util.List;


public class Board {


    // Current token disposition and gamemode: "Spanish Draughts" way

    /*
       Board identifier distribution:
        ------------------
        |  11  05  31  25|
        |10  04  30  24  |
        |  03  29  23  17|
        |02  28  22  16  |
        |  27  21  15  09|
        |26  20  14  08  |
        |  19  13  07  01|
        |18  12  06  00  |
        ------------------
        First square is numbered after "11".
     */

    private List<Square> occupiedByRed;
    private static final int[] INITIAL_RED_POSITIONS_ID =
            {
                    11, 5, 31, 25,
                    10, 4, 30, 24,
                    3, 29, 23, 17
            };

    private List<Square> occupiedByBlack;
    private static final int[] INITIAL_BLACK_POSITIONS_ID =
            {
                    26, 20, 14, 8,
                    19, 13, 7, 1,
                    18, 12, 6, 0
            };

    private List<Square> emptySquares;
    private static final int[] INITIAL_EMPTY_POSITIONS_ID = {12, 13, 14, 15, 16, 17, 18, 19};

    // TODO: Optimizar rendimiento implementando un Bitboard como estructura de datos para el tablero

    public Board() {
        occupiedByRed = new ArrayList<>();
        occupiedByBlack = new ArrayList<>();
        emptySquares = new ArrayList<>();

        initializeBoard();
    }

    public void initializeBoard() {
        for (int id : INITIAL_RED_POSITIONS_ID) {
            occupiedByRed.add(new Square(id, new TokenRed(false, id)));
        }

        for (int id : INITIAL_BLACK_POSITIONS_ID) {
            occupiedByBlack.add(new Square(id, new TokenBlack(false, id)));
        }

        for (int id : INITIAL_EMPTY_POSITIONS_ID) {
            emptySquares.add(new Square(id, null));
        }
    }

    public boolean isOccupied(Square square) {
        return (emptySquares.contains(square));
    }


    public List<Square> getOccupiedByRed() { return occupiedByRed; }

    public List<Square> getOccupiedByBlack() { return occupiedByBlack; }

    public List<Square> getEmptySquares() { return emptySquares; }
}
